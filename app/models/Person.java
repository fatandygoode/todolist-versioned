package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person extends Model {
    public String firstName, lastName, gender, email, password;

    public Person(String firstName, String lastName, String gender, String email, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setEmail(email);
        setPassword(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String newGender) {
        String oldGender = getGender();
        if (newGender != null) {
            this.gender = newGender;
        } else if (oldGender != null && !oldGender.equals("Unspecified")) {
            this.gender = oldGender;
        } else {
            this.gender = "Unspecified";
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Person findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}