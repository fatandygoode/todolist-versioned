<<<<<<< HEAD
import java.util.List;

import play.*;
import play.jobs.*;
import play.test.*;

import models.*;

@OnApplicationStart
public class Bootstrap extends Job {
    public void doJob() {
        if (Member.count() == 0) {
            Fixtures.loadModels("data.yml");
        }
    }
=======
import java.util.List;

import play.*;
import play.jobs.*;
import play.test.*;

import models.*;

@OnApplicationStart
public class Bootstrap extends Job {
    public void doJob() {
        if (Member.count() == 0) {
            Fixtures.loadModels("data.yml");
        }
    }
>>>>>>> 37736341c58f246e72bd823a727c6b4543284102
}