package capstone.msd.conestoga.instantsalenotifier.model;

/**
 * Created by CatherineChoi on 12/15/2017.
 */

public class Store {
    private int id;
    private String name;
    private String address;
    private double lantitude;
    private double longtitude;

    public Store(int id, String name, String address, double lantitude, double longtitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.lantitude = lantitude;
        this.longtitude = longtitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public double getLantitude() {
        return lantitude;
    }

    public void setLantitude(double lantitude) {
        this.lantitude = lantitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
