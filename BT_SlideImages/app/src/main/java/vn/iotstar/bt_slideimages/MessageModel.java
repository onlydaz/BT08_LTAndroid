package vn.iotstar.bt_slideimages;

import java.io.Serializable;
import java.util.List;

public class MessageModel implements Serializable {
    private boolean success;
    private String message;
    private List<Images> result;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Images> getResult() {
        return result;
    }
}

