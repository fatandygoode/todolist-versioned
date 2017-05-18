<<<<<<< HEAD
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
=======
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
>>>>>>> 37736341c58f246e72bd823a727c6b4543284102
