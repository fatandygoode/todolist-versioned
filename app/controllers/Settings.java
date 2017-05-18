package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Settings extends Controller {
  public static void index() {
    Logger.info("Rendering settings");
    Member member = Accounts.getLoggedInMember();
    render ("settings.html", member);
  }
}
