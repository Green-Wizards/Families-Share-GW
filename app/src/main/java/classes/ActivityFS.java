package classes;

import java.io.Serializable;

public class ActivityFS implements Serializable {

    /** ATTRIBUTI **/
    public String group_id;
    public String activity_name;
    public String description;
    public String location;
    public String color;
    public String creator_id;
    public String date;
    public boolean repetition;
    public String repetition_type;
    public boolean different_timeslot;
    public String status;

    public ActivityFS() {}

    public ActivityFS(String group_id, String activity_name,
                      String description, String location, String creator_id, String date) {
        this.group_id = group_id;
        this.activity_name = activity_name;
        this.description = description;
        this.location = location;
        this.creator_id = creator_id;
        this.date = date;
    }
}
