package classes;

import java.io.Serializable;

public class Timeslot implements Serializable {

    /** ATTRIBUTI **/
    public String timeslot_id;
    public String activity_id;

    public Timeslot(){
        // Default constructor required for calls to DataSnapshot.getValue(Profile.class)
    }

    public Timeslot(String timeslot_id, String activity_id){
        this.timeslot_id = timeslot_id;
        this.activity_id = activity_id;
    }
}
