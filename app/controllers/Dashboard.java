package controllers;

import models.Member;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    HashMap<Date, Assessment> assessments = member.assessments;
    render("dashboard.html", member, assessments);
  }

  public static void addAssessment(double weight, double chest, double thigh, double upperArm,
                                   double waist, double hips) {
    Member member = Accounts.getLoggedInMember();
    Date date = new Date();
    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, null);
    member.assessments.put(date, assessment);
    member.save();
    Logger.info("Adding Assessment");
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long id, Long assessmentid) {
    Member member = Member.findById(id);
    Assessment assessment = Assessment.findById(assessmentid);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting Assessment");
    redirect("/dashboard");
  }
}