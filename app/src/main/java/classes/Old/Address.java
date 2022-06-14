package classes;

public class Address {

    /** ATTRIBUTI **/
    public String address_id;
    public String street;
    public String number;
    public String city;

    public Address(String address_id, String street, String number, String city) {
        this.address_id = address_id;
        this.street = street;
        this.number = number;
        this.city = city;
    }
}
