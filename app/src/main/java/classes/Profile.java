package classes;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

@IgnoreExtraProperties
public class Profile implements Serializable {

    /** ATTRIBUTI **/
    public Integer user_id;
    public String address_id;
    public String email;
    public String password;
    public String phone;
    public String phone_type;
    public String contact_option;       // Il valore di default dovrebbe essere "email"
    public String description;
    public String image_id;
    public String given_name;
    public String family_name;
    public Boolean visible;
    public Boolean suspended;

    public Profile() {
        // Default constructor required for calls to DataSnapshot.getValue(Profile.class)
    }

    public Profile(/*String address_id,*/ String given_name, String family_name, String email,
                   String password, String phone, boolean visible) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.given_name = given_name;
        this.family_name = family_name;
        this.visible = visible;
    }
}
