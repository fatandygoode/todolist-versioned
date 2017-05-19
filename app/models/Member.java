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

    /**
     * Calculate a member's body mass index, weight divided by height squared
     * @return  Member's BMI (kg m ^-2) truncated to two decimal places
     */
    public double calculateBMI(){
        return toTwoDecimalPlaces(getCurrentWeight() / (getHeight()/100 * getHeight()/100));
    }
    /**
     * helper method to truncate numbers to two decimal places
     * @return number to two decimal places
     */
    private static double toTwoDecimalPlaces(double num){
        return (int) (num * 100 ) / 100.0;
    }

    public String isIdealBodyWeight() {
        double inchHeight = convertHeightMetresToInches(getHeight());
        if (inchHeight > 60) {
            inchHeight = inchHeight - 60;
        } else {
            inchHeight = 0;
        }
        double idealBodyWeight = 2.3 * inchHeight;
        if (getGender().equals("Male")) {
            idealBodyWeight = idealBodyWeight + 50;
        } else {
            idealBodyWeight = idealBodyWeight + 45.5;
        }
        if (idealBodyWeight <= getCurrentWeight() + 2 && idealBodyWeight >= getCurrentWeight() - 2) {
            return "Ideal Body weight";
        } else {
            return "Not ideal body weight";
        }
    }

    /**
     * Converts a member's height from metres to inches, 1m = 39.37 inches.
     * @return  Member's height in inches truncated to two decimal places
     */
    public double convertHeightMetresToInches(double height) {
        return toTwoDecimalPlaces(height * 39.37/100);
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
        return getAssessments().get(0);
    }


    /**
     * Determines the BMI catergory to which the member belongs.
     * The categories are determined as follows:
     * less than 15 "VERY SEVERELY UNDERWEIGHT"
     * between 15 (inclusive) and 16 (exclusive) "SEVERELY UNDERWEIGHT"
     * between 16 (inclusive) and 18.5 (exclusive) "UNDERWEIGHT"
     * between 18.5 (inclusive) and 25 (exclusive) "NORMAL"
     * between 25 (inclusive) and 30 (exclusive) "OVERWEIGHT"
     * between 30 (inclusive) and 35 (exclusive) "MODERATELY OBESE"
     * between 35 (inclusive) and 40 (exclusive) "SEVERELY OBESE"
     * greater than 40 "VERY SEVERELY OBESE"
     * @return  Member's BMI category
     */
    public String determineBMICategory(double bmiValue) {
        if (bmiValue < 15) {
            return "Very severely underweight";
        } else if (bmiValue >= 15 && bmiValue < 16) {
            return "Severely underweight";
        } else if (bmiValue >= 16 && bmiValue < 18.5) {
            return "Underweight";
        } else if (bmiValue >= 18.5 && bmiValue < 25) {
            return "Normal";
        } else if (bmiValue >= 25 && bmiValue < 30) {
            return "Overweight";
        } else if (bmiValue >= 30 && bmiValue < 35) {
            return "Moderately Obese";
        } else if (bmiValue >= 35 && bmiValue < 40) {
            return "Severely Obese";
        } else {
            return "Very severely obese";
        }
    }
}