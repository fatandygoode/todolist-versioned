package controllers;

import models.Assessment;
import models.Member;
import models.Person;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static controllers.Accounts.*;

public class Dashboard extends Controller {

	public static void index() {
		Person person = getLoggedInPerson();
		if (person instanceof Member) {
			Logger.info("Rendering Dashboard");
			Member member = (Member) person;
			List<Assessment> assessments = member.getAssessments();
			render("dashboard.html", member, assessments);
		} else if (person instanceof Trainer) {
			Logger.info("Rendering Trainer Homepage");
			List<Member> members = getMembers();
			render("trainerhomepage.html", person, members);
		}
	}

    public static void addAssessment(Date date, double weight, double chest, double thigh,
									   double upperArm, double waist, double hips) {
        Person person = getLoggedInPerson();
        Member member =(Member) person;
        Assessment assessment = new Assessment(date, weight, chest, thigh, upperArm, waist, hips);
        member.getAssessments().add(assessment);
        member.save();
        Logger.info("Adding Assessment" + date);
        redirect("/dashboard");
    }

	public static void deleteAssessment(Long assessmentId) {
        Member member = getLoggedInMember();
		Assessment assessment = Assessment.findById(assessmentId);
		member.getAssessments().remove(assessment);
		member.save();
		assessment.delete();
		Logger.info("Deleting " + assessment.getDate());
		redirect("/dashboard");
	  }

    public static void deleteMember(Long memberId) {
	    Member member = Member.findById(memberId);
        Logger.info("Deleting " + member.getEmail());
        member.delete();
        redirect("/dashboard");
    }

    public static List<Member> getMembers() {
        return Member.findAll();
    }

    public static void memberAssessments(Long memberId) {
        Member member = Member.findById(memberId);
        List<Assessment> assessments = member.getAssessments();
        render("assessments.html", member, assessments);
    }

    public static void addComment(Long assessmentId) {
        Assessment assessment = Assessment.findById(assessmentId);
        render("/comment.html", assessment);
    }

    public static void setComment(String comment, Long assessmentId) {
        Assessment assessment = Assessment.findById(assessmentId);
        assessment.setComment(comment);
        Logger.info("Adding comment " + assessment.getDate());
        redirect("/dashboard");
    }
}