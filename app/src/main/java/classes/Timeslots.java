package classes;

import java.io.Serializable;

public class Timeslots implements Serializable {

    /** ATTRIBUTI **/
    public String date;
    public String startTime;
    public String endTime;
    public String activity_id;

    public Timeslots(){
        // Default constructor required for calls to DataSnapshot.getValue(Profiles.class)
    }

    public Timeslots(String data, String oraInizio, String oraFine, String activity_id) {
        this.date = data;
        this.startTime = oraInizio;
        this.endTime = oraFine;
        this.activity_id = activity_id;
    }
}
