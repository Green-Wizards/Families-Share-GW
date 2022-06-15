package classes;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Date;

@IgnoreExtraProperties
public class Dependents implements Serializable {

    /** ATTRIBUTI **/
    public String dependent_id;
    public String dependent_type;
    public String given_name;
    public String family_name;
    public String gender;
    public String birthdate;
    public String image_id;
    public String background;
    public boolean suspended;
    public String infoList;
    public String gradoParentela;
    public String tutor_id;

    public Dependents(){}

    public Dependents(String given_name, String family_name, String gender, String birthdate, String parentela, String infoList, String tutor_id) {
        this.given_name = given_name;
        this.family_name = family_name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.gradoParentela = parentela;
        this.infoList = infoList;
        this.tutor_id = tutor_id;
    }
}
