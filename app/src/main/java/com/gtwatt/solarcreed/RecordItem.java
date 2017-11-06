package com.gtwatt.solarcreed;

/**
 * Created by Gtwatt on 11/1/17.
 */

public class RecordItem {
    String type;
    String details;
    String count;

    public RecordItem(String type, String details, String count) {
        this.type = type;
        this.details = details;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
