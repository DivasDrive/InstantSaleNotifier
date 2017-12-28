package capstone.msd.conestoga.instantsalenotifier.model;

import capstone.msd.conestoga.instantsalenotifier.R;

/**
 * Created by CatherineChoi on 12/27/2017.
 */

public class StoreCategory {
    private String title;
    private int    resourceId;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }



    public StoreCategory(String title, int resourceId){
        this.title = title;
        this.resourceId = resourceId;
    }

}