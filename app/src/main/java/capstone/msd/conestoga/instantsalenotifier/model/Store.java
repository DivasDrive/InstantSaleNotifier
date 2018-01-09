package capstone.msd.conestoga.instantsalenotifier.model;

/**
 * Created by CatherineChoi on 12/15/2017.
 */

public class Store {
    private int id;
    private String name;
    private String emailAddress;
    private String phoneNumber;
    private String address;
    private String city;
    private String Province;
    private String PostalCode;
    private double lantitude;
    private double longtitude;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return Province;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public double getLantitude() {
        return lantitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public Store(int id, String name, String emailAddress, String phoneNumber, String address, String city, String province, String postalCode, double lantitude, double longtitude) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        Province = province;
        PostalCode = postalCode;
        this.lantitude = lantitude;
        this.longtitude = longtitude;
    }
}
