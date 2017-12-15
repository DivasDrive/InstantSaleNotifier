package capstone.msd.conestoga.instantsalenotifier.model;

/**
 * Created by CatherineChoi on 12/15/2017.
 */

public class Store {
    private int id;
    private String name;

    public Store(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
