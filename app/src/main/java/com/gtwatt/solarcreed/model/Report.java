package com.gtwatt.solarcreed.model;

import com.orm.SugarRecord;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Gtwatt on 11/5/17.
 */
public class Report extends SugarRecord {



    public Report() {
    }

    public Report(int id, int goodEgg, int badEgg, int mortality, int sickBirds, int usedFeed, int newFeed, int pen, String penssid, int culling) {
        this.id = id;
        this.goodEgg = goodEgg;
        this.badEgg = badEgg;
        this.mortality = mortality;
        this.sickBirds = sickBirds;
        this.usedFeed = usedFeed;
        this.newFeed = newFeed;
        this.pen = pen;
        String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
        this.date = dateString;
        this.ssid = UUID.randomUUID().toString();
        this.penssid = penssid;
        this.culling = culling;

    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getPen() {
        return pen;
    }

    public void setPen(int pen) {
        this.pen = pen;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getGoodEgg() {
        return String.valueOf(goodEgg);
    }

    public void setGoodEgg(int goodEgg) {
        this.goodEgg = goodEgg;
    }

    public String getBadEgg() {
        return String.valueOf(badEgg);
    }

    public void setBadEgg(int badEgg) {
        this.badEgg = badEgg;
    }

    public String getMortality() {
        return String.valueOf(mortality);
    }

    public void setMortality(int mortality) {
        this.mortality = mortality;
    }

    public String getSickBirds() {
        return String.valueOf(sickBirds);
    }

    public void setSickBirds(int sickBirds) {
        this.sickBirds = sickBirds;
    }

    public String getUsedFeed() {
        return String.valueOf(usedFeed);
    }

    public void setUsedFeed(int usedFeed) {
        this.usedFeed = usedFeed;
    }

    public String getNewFeed() {
        return String.valueOf(newFeed);
    }

    public void setNewFeed(int newFeed) {
        this.newFeed = newFeed;
    }

    int id;
    int goodEgg;
    int badEgg;
    int mortality;
    int sickBirds;
    int usedFeed;
    int newFeed;
    String date;
    int pen;
    String ssid;
    String penssid;
    int culling;

    public String getCulling() {
        return String.valueOf(culling);
    }

    public void setCulling(int culling) {
        this.culling = culling;
    }
}
