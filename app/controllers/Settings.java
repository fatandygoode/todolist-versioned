package controllers;

import models.Member;
import models.Person;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;
import static controllers.Accounts.*;

public class Settings extends Controller {
  public static void index() {
    Logger.info("Rendering settings");
    Person person = getLoggedInPerson();
    if (person instanceof Member) {
        Member member = (Member) person;
        render ("settings.html", member);
    } else if (person instanceof Trainer) {
        Trainer trainer = (Trainer) person;
        render("trainersettings.html", trainer);
    }
  }
}
