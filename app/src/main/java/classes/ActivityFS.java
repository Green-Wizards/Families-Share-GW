package classes;

public class ActivityFS {

    /** ATTRIBUTI **/
    public String activity_id;
    public String group_id;
    public String group_name;
    public String description;
    public String location;
    public String color;
    public String creator_id;
    public boolean repetition;
    public String repetition_type;
    public boolean different_timeslot;
    public String status;

    public ActivityFS(){
        // Default constructor required for calls to DataSnapshot.getValue(Profile.class)
    }

    public ActivityFS(String activity_id, String group_id, String group_name, String description, String location, String color, String creator_id,
                      boolean repetition, String repetition_type, boolean different_timeslot, String status){
        this.activity_id = activity_id;
        this.group_id = group_id;
        this.group_name = group_name;
        this.description = description;
        this.location = location;
        this.color = color;
        this.creator_id = creator_id;
        this.repetition = repetition;
        this.repetition_type = repetition_type;
        this.different_timeslot = different_timeslot;
        this.status = status;
    }
}
