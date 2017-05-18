<<<<<<< HEAD
package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessments = member.assessments;
    render("dashboard.html", member, assessments);
  }

  public static void addAssessment(Date date, Time time, double weight, double chest, double thigh, double upperArm, double waist, double hips) {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
    member.assessments.add(assessment);
    member.save();
    Logger.info("Adding Assessment" + weight);
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long assessmentId) {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = Assessment.findById(assessmentId);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting " + assessment.getWeight());
    redirect("/dashboard");
  }
=======
package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessments = member.assessments;
    render("dashboard.html", member, assessments);
  }

  public static void addAssessment(Date date, Time time, double weight, double chest, double thigh, double upperArm, double waist, double hips) {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
    member.assessments.add(assessment);
    member.save();
    Logger.info("Adding Assessment" + weight);
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long assessmentId) {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = Assessment.findById(assessmentId);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting " + assessment.getWeight());
    redirect("/dashboard");
  }
>>>>>>> 37736341c58f246e72bd823a727c6b4543284102
}