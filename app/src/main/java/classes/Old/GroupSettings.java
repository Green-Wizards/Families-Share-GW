package classes.Old;

import java.io.Serializable;

public class GroupSettings implements Serializable {

    /** ATTRIBUTI **/
    public String settings_id;
    public String group_id;
    public boolean open;
    public boolean visible;
    public boolean publicity;

    public GroupSettings() {
        // Default constructor required for calls to DataSnapshot.getValue(Profiles.class)
    }

    public GroupSettings(String group_id, boolean open, boolean visible, boolean publicity) {
        this.group_id = group_id;
        this.open = open;
        this.visible = visible;
        this.publicity = publicity;
    }
}
