package classes;

import java.io.Serializable;

public class Notifications implements Serializable {

    /** ATTRIBUTI **/
    public String sender_id;
    public String receiver_id;
    public String subject;
    public boolean read;

    public Notifications(){}

    public Notifications(String sender_id, String receiver_id, String subject, boolean read) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.subject = subject;
        this.read = read;
    }
}
