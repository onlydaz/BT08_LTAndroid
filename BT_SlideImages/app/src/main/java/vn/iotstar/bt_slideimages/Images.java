package vn.iotstar.bt_slideimages;

import java.io.Serializable;

public class Images implements Serializable {
    private int imageId;

    public Images(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}