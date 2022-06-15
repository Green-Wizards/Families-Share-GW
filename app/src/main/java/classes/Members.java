package classes;

import java.io.Serializable;

public class Members implements Serializable {

    /** ATTRIBUTI **/
    public String user_id;
    public String group_id;
    public boolean admin;
    public boolean user_accepted;

    public Members(){
        // Default constructor required for calls to DataSnapshot.getValue(Profiles.class)
    }

    public Members(String user_id, String group_id, boolean admin, boolean user_accepted){
        this.user_id = user_id;
        this.group_id = group_id;
        this.admin = admin;
        this.user_accepted = user_accepted;
    }
}
