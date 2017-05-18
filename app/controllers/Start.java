package controllers;

import play.Logger;
import play.mvc.Controller;
import play.test.Fixtures;

public class Start extends Controller {
  public static void index() {
      //Fixtures.deleteAllModels();
      //Fixtures.loadModels("data.yml");
      Logger.info("Rendering Start");
      render ("start.html");
  }
}
