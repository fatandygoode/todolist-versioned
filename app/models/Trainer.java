package models;

/**
 * Created by John on 24/04/2017.
 */
public class Trainer extends Person {
    private String speciality;

    public Trainer(String email, String password, String firstName, String lastName,
                   String gender, String speciality) {
        super(email, password, firstName, lastName, gender);
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

}
