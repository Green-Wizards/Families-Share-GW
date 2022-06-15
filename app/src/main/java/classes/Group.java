package classes;

import java.io.Serializable;

public class Group implements Serializable {

    /** ATTRIBUTI **/
    public String group_id;
    public String name;
    public String description;
    public String location;
    public String calendar_id;
    public String settings_id;
    public String image_id;
    public String background;
    public String owner_id;
    public String contact_info;
    public String contact_type;
    public boolean visibility;

    public Group() {
        // Default constructor required for calls to DataSnapshot.getValue(Profile.class)
    }

    public Group(String name, String description, String location, String settings_id,
                 String owner_id, String contact_type, String contact_info, boolean visibility) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.settings_id = settings_id;
        this.owner_id = owner_id;
        this.contact_type = contact_type;
        this.contact_info = contact_info;
        this.visibility = visibility;
    }
}
