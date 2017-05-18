package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Assessment extends Model {
    private Date date;
    private Time time;
    private double weight, chest, thigh, upperArm, waist, hips;
    private String comment;

    public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips) {
        setWeight(weight);
        setChest(chest);
        setThigh(thigh);
        setUpperArm(upperArm);
        setWaist(waist);
        setHips(hips);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getUpperArm() {
        return upperArm;
    }

    public void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}