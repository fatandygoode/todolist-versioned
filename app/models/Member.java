package models;

import play.db.jpa.Model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class Member extends Person
{
    public double height, startingWeight;
    public HashMap<Date, Assessment> assessments;

    public Member(String email, String password,String firstName, String lastName, String gender,
                  double height, double startingWeight) {
        super(email, password, firstName, lastName, gender);
        this.height = height;
        this.startingWeight = startingWeight;
        assessments = new HashMap<>();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getStartingWeight() {
        return startingWeight;
    }

    public void setStartingWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }

    public double getCurrentWeight() {
        double weight = getStartingWeight();
        if (latestAssessment() != null) {
            weight = latestAssessment().getWeight();
        }
        return weight;
    }

    public SortedSet<Date> sortedAssessmentDates() {
        return new TreeSet<>(assessments.keySet());
    }


    public Assessment latestAssessment() {
        return assessments.get(sortedAssessmentDates().last());
    }

    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}