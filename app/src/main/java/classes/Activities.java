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
    public int minUser;
    public int maxUser;
    public int minDependent;
    public int maxDependent;

    public Activities(){
        // Default constructor required for calls to DataSnapshot.getValue(Profile.class)
    }

    public Activities(String group_id, String activity_name,
                      String description, String location, String creator_id, String date) {
        this.group_id = group_id;
        this.activity_name = activity_name;
        this.description = description;
        this.location = location;
        this.creator_id = creator_id;
        this.date = date;
    }
}
