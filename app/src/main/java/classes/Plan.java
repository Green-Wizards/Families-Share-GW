package classes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Plan {

    /** ATTRIBUTI **/
    public String state;
    public String plan_id;
    public String group_id;
    public String name;
    public String description;
    public String location;
    public String creator_id;
    public Date from;
    public Date to;
    public Date deadline;
    public int ratio;
    public int min_volunteers;
    public int max_volunteers;
    public int max_dependents;
    public String category;
    public ArrayList<Subscription> solution;
    public ArrayList<Participant> participants;
}
