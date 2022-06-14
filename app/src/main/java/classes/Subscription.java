package classes;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Subscription implements Serializable {

    /** ATTRIBUTI **/
    public String dependents; //utenti a carico
    public String volunteers; //utenti di FS
    public String timeslot_id;

    public Subscription() {
    }

    public Subscription(String dependents, String volunteers, String timeslot_id) {
        this.dependents = dependents;
        this.volunteers = volunteers;
        this.timeslot_id = timeslot_id;
    }
}
