package capstone.msd.conestoga.instantsalenotifier.model;

/**
 * Created by CatherineChoi on 1/2/2018.
 */

public class Discount {
    private String name;

    public Discount(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
