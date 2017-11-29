package com.gtwatt.solarcreed.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Gtwatt on 11/6/17.
 */

public class Expense extends SugarRecord{

    int feedExpense;
    int miscExpense;
    int birdExpense;
    int id;
    String date;
    @Unique
    String ssid;



    public Expense() {
    }


    public void setId(int id) {
        this.id = id;
    }

    public Expense(int feedExpense, int eggExpense, int birdExpense) {
        this.feedExpense = feedExpense;
        this.miscExpense = eggExpense;
        this.birdExpense = birdExpense;
        String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
        this.date = dateString;
        this.ssid = UUID.randomUUID().toString();


    }

    public String getFeedExpense() {
        return String.valueOf(feedExpense);
    }

    public void setFeedExpense(int feedExpense) {
        this.feedExpense = feedExpense;
    }

    public String getMiscExpense() {
        return String.valueOf(miscExpense);
    }

    public void setMiscExpense(int miscExpense) {
        this.miscExpense = miscExpense;
    }

    public String getBirdExpense() {
        return String.valueOf(birdExpense);
    }

    public void setBirdExpense(int birdExpense) {
        this.birdExpense = birdExpense;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
