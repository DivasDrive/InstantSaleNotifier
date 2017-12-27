package capstone.msd.conestoga.instantsalenotifier.model;

/**
 * Created by CatherineChoi on 12/27/2017.
 */

public class StoreCategory {

    private String title;
    private int imageUrl;

    public StoreCategory(String title, int imageUrl){

        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getImageUrl() {
        return imageUrl;
    }
}