package classes;

public class Member {

    /** ATTRIBUTI **/
    public String user_id;
    public String group_id;
    public boolean admin;
    public boolean group_accepted;
    public boolean user_accepted;

    public Member(){
        // Default constructor required for calls to DataSnapshot.getValue(Profile.class)
    }

    public Member(String user_id, String group_id, boolean admin, boolean group_accepted, boolean user_accepted){
        this.user_id = user_id;
        this.group_id = group_id;
        this.admin = admin;
        this.group_accepted = group_accepted;
        this.user_accepted = user_accepted;
    }
}
