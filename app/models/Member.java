package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Person {
    private double height, startingWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Todo> todolist = new ArrayList<Todo>();

    public Member(String firstName, String lastName, String gender, String email, String password,
                  double height, double startingWeight) {
        super(firstName, lastName, gender, email, password);
        setHeight(height);
        setStartingWeight(startingWeight);
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

    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }
}