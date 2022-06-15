package classes;

import java.io.Serializable;

public class Activities implements Serializable {

    /** ATTRIBUTI **/
    public String group_id;
    public String activity_name;
    public String description;
    public String location;
    public String creator_id;
    public String date;
    public boolean different_timeslot;
    public String status;
    public int minUsers;
    public int maxUsers;
    public int minDependents;
    public int maxDependents;

    public Activities(){
        // Default constructor required for calls to DataSnapshot.getValue(Profiles.class)
    }

    public Activities(String group_id, String activity_name, String description, String location,
                      String creator_id, String date, boolean different_timeslot, String status,
                      int minUsers, int maxUsers, int minDependents, int maxDependents) {
        this.group_id = group_id;
        this.activity_name = activity_name;
        this.description = description;
        this.location = location;
        this.creator_id = creator_id;
        this.date = date;
        this.different_timeslot = different_timeslot;
        this.status = status;
        this.minUsers = minUsers;
        this.maxUsers = maxUsers;
        this.minDependents = minDependents;
        this.maxDependents = maxDependents;
    }
}
