package models;

import javax.persistence.Entity;

/**
 * Created by John on 24/04/2017.
 */

@Entity
public class Trainer extends Person {
    private String speciality;

    public Trainer(String firstName, String lastName, String gender, String email, String password,
                    String speciality) {
        super(firstName, lastName, email, password, gender);
        setSpeciality(speciality);
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

}
