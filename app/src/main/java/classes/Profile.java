package classes;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class Profile implements Serializable {

    /** ATTRIBUTI **/
    public String email;
    public String password;
    public String phone;
    public String phone_type;
    public String contact_option;       // Il valore di default dovrebbe essere "email"
    public String description;
    public String given_name;
    public String family_name;
    public String area;
    public String address;
    public Boolean visible;


    public Profile() {
        // Default constructor required for calls to DataSnapshot.getValue(Profile.class)
    }

    public Profile(/*String address_id,*/ String given_name, String family_name, String email,
                   String password, String phone, boolean visible, String phone_type, String contact_option,
                  String description, String area, String address) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.given_name = given_name;
        this.family_name = family_name;
        this.phone_type = phone_type;
        this.contact_option = contact_option;
        this.description = description;
        this.visible = visible;
        this.area = area;
        this.address = address;
    }
}
