package controllers;

import models.Member;
import models.Person;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;


public class Accounts extends Controller {
    public static void signup() {
        render("signup.html");
    }

    public static void login() {
        render("login.html");
    }

    public static void register(String firstName, String lastName, String gender, String email, String password,
                                double height, double startingWeight) {
        Logger.info("Registering new user " + email);
        Member member = new Member(firstName, lastName, gender, email, password, height, startingWeight);
        member.save();
        redirect("/");
    }

    public static void update(String firstName, String lastName, String gender, String email, String password,
                                double height, double startingWeight, String speciality) {
        Logger.info("Updating settings for user: " + email);
        Person person = getLoggedInPerson();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setGender(gender);
        person.setEmail(email);
        person.setPassword(password);
        if (person instanceof Member){
            Member member =(Member) person;
            member.setHeight(height);
            member.setStartingWeight(startingWeight);
        } else if (person instanceof Trainer) {
            Trainer trainer =(Trainer) person;
            trainer.setSpeciality(speciality);
        }
        person.save();
        redirect("/dashboard");
    }

    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ":" + password);
        Person person = Person.findByEmail(email);
        if (person != null && person.checkPassword(password)) {
            Logger.info("Authentication successful");
            session.put("logged_in_PersonId", person.id);
            redirect("/dashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static void logout() {
        session.clear();
        redirect ("/");
    }

    public static Member getLoggedInMember() {
        return (Member) getLoggedInPerson();
    }

    public static Trainer getLoggedInTrainer() {
        return (Trainer) getLoggedInPerson();
    }

    public static Person getLoggedInPerson() {
        Person person = null;
        if (session.contains("logged_in_PersonId")) {
            String personId = session.get("logged_in_PersonId");
            person = Person.findById(Long.parseLong(personId));
        } else {
            login();
        }
        return person;
    }
}