package com.gtwatt.solarcreed;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Gtwatt on 11/2/17.
 */

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "solarCreed";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_FARM_DETAIL_SAVED = "ISFARMDETAILSAVED";
    private static final String IS_SIGN_IN = "isSignIn";


    private static final String FARM_NAME = "firstName";
    private static final String NOTIFICATION = "notification";
    private static final String EMAIL = "emailName";
    private static final String TOTAL_BIRD = "totalBird";
    private static final String TOTAL_FEED = "totalFeed";

    private static final String PHONE_Number = "phoneNumber";



    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setDetailedSaved(boolean isFirstTime) {
        editor.putBoolean(IS_FARM_DETAIL_SAVED, isFirstTime);
        editor.commit();
    }

    public boolean isDetailSaved() {
        return pref.getBoolean(IS_FARM_DETAIL_SAVED, false);
    }

    public void setIsSignIn(boolean isFirstTime) {
        editor.putBoolean(IS_SIGN_IN, isFirstTime);
        editor.commit();
    }

    public boolean isSignIn() {
        return pref.getBoolean(IS_SIGN_IN, false);
    }

    public void setFarmName(String name){
        editor.putString(FARM_NAME, name);
        editor.commit();

    }

    public String getFarmName(){
        return pref.getString(FARM_NAME, "");
    }

    public void setEmail(String name){
        editor.putString(EMAIL, name);
        editor.commit();

    }

    public String getPhoneNumber(){
        return pref.getString(PHONE_Number, "");
    }

    public void setPhoneNumber(String number){
        editor.putString(PHONE_Number, number);
        editor.commit();

    }

    public int getFeed(){
        return pref.getInt(TOTAL_FEED, 0);
    }

    public void setFeed(int number){
        editor.putInt(TOTAL_FEED, number);
        editor.commit();
    }

    public int getBirdTotal(){
        return pref.getInt(TOTAL_BIRD, 0);
    }

    public void setBirdTotal(int number){
        editor.putInt(TOTAL_BIRD, number);
        editor.commit();
    }

    public String getEmail(){
        return pref.getString(EMAIL, "");
    }

    public void setShouldNotify(boolean isFirstTime) {
        editor.putBoolean(NOTIFICATION, isFirstTime);
        editor.commit();
    }

    public boolean shouldNotify() {
        return pref.getBoolean(NOTIFICATION, true);
    }

}