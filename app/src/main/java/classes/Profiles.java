package classes;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class Profiles implements Serializable {

    /** ATTRIBUTI **/
    public String email;
    public String password;
    public String phone;
    public String contact_option;
    public String description;
    public String given_name;
    public String family_name;
    public String area;
    public String address;


    public Profiles() {
        // Default constructor required for calls to DataSnapshot.getValue(Profiles.class)
    }

    public Profiles(String given_name, String family_name, String email,
                    String password, String phone, String contact_option,
                    String description, String area, String address) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.given_name = given_name;
        this.family_name = family_name;
        this.contact_option = contact_option;
        this.description = description;
        this.area = area;
        this.address = address;
    }
}
