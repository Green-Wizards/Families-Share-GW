package classes;

import java.io.Serializable;

public class FamilyNucleus implements Serializable {

    /** ATTRIBUTI **/
    public String user_list;
    public String users_id;

    public FamilyNucleus(){}

    public FamilyNucleus(String user_list, String users_id) {
        this.user_list = user_list;
        this.users_id = users_id;
    }
}
