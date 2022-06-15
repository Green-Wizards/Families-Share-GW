package classes;

import java.io.Serializable;

public class Timeslots implements Serializable {

    /** ATTRIBUTI **/
    public String data;
    public String oraInizio;
    public String oraFine;
    public String activity_id;

    public Timeslots(){
        // Default constructor required for calls to DataSnapshot.getValue(Profiles.class)
    }

    public Timeslots(String data, String oraInizio, String oraFine, String activity_id) {
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.activity_id = activity_id;
    }
}
