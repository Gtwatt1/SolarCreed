package com.gtwatt.solarcreed.model;

import com.orm.SugarRecord;

import java.util.UUID;

/**
 * Created by Gtwatt on 11/21/17.
 */

public class Pen extends SugarRecord{

    int id;
    int birdsNumber;
    String BirdsType;
    String name;
    String assignedSupervisor;
    int penNum;


    String SSID;



    public Pen() {
    }


    public int getPenNum() {
        return penNum;
    }

    public void setPenNum(int penNum) {
        this.penNum = penNum;
    }

    public Pen(int birdsNumber, String birdsType, String name, String assignedSupervisor, int penNum) {
        this.birdsNumber = birdsNumber;
        BirdsType = birdsType;
        this.name = name;
        this.assignedSupervisor = assignedSupervisor;
        this.penNum = penNum;
        this.SSID = UUID.randomUUID().toString();

    }

    public int getBirdsNumber() {
        return birdsNumber;
    }

    public void setBirdsNumber(int birdsNumber) {
        this.birdsNumber = birdsNumber;
    }

    public String getBirdsType() {
        return BirdsType;
    }

    public void setBirdsType(String birdsType) {
        BirdsType = birdsType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSSID() {
        return SSID;
    }

    public String getAssignedSupervisor() {
        return assignedSupervisor;
    }

    public void setAssignedSupervisor(String assignedSupervisor) {
        this.assignedSupervisor = assignedSupervisor;
    }


}
