package com.gtwatt.solarcreed.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gtwatt on 11/6/17.
 */

public class Expense {

    int feedExpense;
    int miscExpense;
    int birdExpense;
    int id;
    String date;

    public Expense() {
    }

    public int getId() {
        return id;
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
