package classes;

import java.io.Serializable;

public class Groups implements Serializable {

    /** ATTRIBUTI **/
    public String name;
    public String description;
    public String location;
    public String owner_id;
    public String contact_info;
    public String contact_type;
    public boolean visibility;

    public Groups() {
        // Default constructor required for calls to DataSnapshot.getValue(Profiles.class)
    }

    public Groups(String name, String description, String location, String owner_id, String contact_type, String contact_info, boolean visibility) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.owner_id = owner_id;
        this.contact_type = contact_type;
        this.contact_info = contact_info;
        this.visibility = visibility;
    }
}
