package com.gtwatt.solarcreed;

/**
 * Created by Gtwatt on 11/1/17.
 */

public class HomeItem {

    String homeType;
    String homePics;
    String addString;
    String homeCount;

    public String getHomeType() {
        return homeType;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public String getHomePics() {
        return homePics;
    }

    public void setHomePics(String homePics) {
        this.homePics = homePics;
    }

    public String getAddString() {
        return addString;
    }

    public void setAddString(String addString) {
        this.addString = addString;
    }

    public String getHomeCount() {
        return homeCount;
    }

    public void setHomeCount(String homeCount) {
        this.homeCount = homeCount;
    }

    public HomeItem(String homeType, String homePics, String addString, String homeCount) {

        this.homeType = homeType;
        this.homePics = homePics;
        this.addString = addString;
        this.homeCount = homeCount;
    }
}
