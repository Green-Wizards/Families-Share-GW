package classes;

import java.io.Serializable;

public class Timeslot implements Serializable {

    /** ATTRIBUTI **/
    public String timeslot_id;
    public String timeslot;

    public Timeslot(){
        // Default constructor required for calls to DataSnapshot.getValue(Profile.class)
    }

    public Timeslot(String timeslot_id, String timeslot){
        this.timeslot_id = timeslot_id;
        this.timeslot = timeslot;
    }
}
