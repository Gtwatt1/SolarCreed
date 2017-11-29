package com.gtwatt.solarcreed.model;

import com.orm.SugarRecord;

import java.text.DateFormat;
import java.util.UUID;

/**
 * Created by Gtwatt on 11/6/17.
 */

public class Vaccine extends SugarRecord {
    String type;
    int numberBirds;
    String date;
    String detail;
    int id;
    boolean completed;
    String ssid;


    public Vaccine(String type, int numberBirds, String date, String detail) {
        this.type = type;
        this.numberBirds = numberBirds;
        this.date = date;
        this.detail = detail;
        completed = false;
        this.ssid = UUID.randomUUID().toString();

    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Vaccine() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumberBirds() {
        return String.valueOf(numberBirds);
    }

    public void setNumberBirds(int numberBirds) {
        this.numberBirds = numberBirds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }



}
