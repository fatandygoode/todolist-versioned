package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Member extends Person {
    private double height, startingWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments;

    public Member(String firstName, String lastName, String gender, String email, String password,
                  double height, double startingWeight) {
        super(firstName, lastName, gender, email, password);
        setHeight(height);
        setStartingWeight(startingWeight);
        assessments = new ArrayList<>();
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

    public List<Assessment> getAssessments() {
        Collections.sort(assessments, new Comparator<Assessment>() {
             public int compare(Assessment o1, Assessment o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
    });
        Collections.reverse(assessments);
        return assessments;
    }

    public Assessment getAssessment(int index) {
        return getAssessments().get(index);
    }

    public int numberOfAssessments () {
        return getAssessments().size();
    }

    public double getCurrentWeight() {
        double weight = getStartingWeight();
        if (latestAssessment() != null) {
            weight = latestAssessment().getWeight();
        }
        return weight;
    }

    public Assessment latestAssessment() {
        return assessments.get(numberOfAssessments()-1);
    }
}