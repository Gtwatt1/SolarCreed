package com.gtwatt.solarcreed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gtwatt on 11/5/17.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;

        private static final String DATABASE_NAME = "ReportDb";

        private static final String TABLE_REPORT = "reportTable";

        private static final String KEY_ID = "id";
        private static final String KEY_BAD_EGG = "badegg";
        private static final String KEY_GOOD_EGG = "goodegg";
        private static final String KEY_USED_FEED = "usedfeed";
        private static final String KEY_NEW_FEED = "newfeed";
        private static final String KEY_MORTALITY = "mortality";
        private static final String KEY_SICK_BIRD = "sickbird";

        public DataBaseHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // Creating Tables
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_REPORT + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MORTALITY + " INTERGER,"
                    + KEY_SICK_BIRD + " INTERGER" + KEY_NEW_FEED + " INTERGER," + KEY_USED_FEED +
                    " INTERGER," + KEY_BAD_EGG + " INTERGER,"+ KEY_GOOD_EGG + " INTERGER," +")";
            db.execSQL(CREATE_CONTACTS_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT);

            onCreate(db);
        }

        public void addReport(Report report) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_BAD_EGG, report.getBadEgg());
            values.put(KEY_GOOD_EGG, report.getGoodEgg());

            values.put(KEY_USED_FEED, report.getUsedFeed());
            values.put(KEY_NEW_FEED, report.getNewFeed());


            values.put(KEY_MORTALITY, report.getMortality());
            values.put(KEY_SICK_BIRD, report.getSickBirds());

            db.insert(TABLE_REPORT, null, values);
            db.close();
            Log.d("Database handler", "addCryptoConv: Success" );
        }

        public List<Report> getAllReport() {
            List<Report> reportList = new ArrayList<Report>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_REPORT;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    Report report = new Report();
//                report.setID(Integer.parseInt(cursor.getString(0)));
                    report.setMortality(cursor.getInt(1));
                    report.setSickBirds(cursor.getInt(2));

                    report.setNewFeed(cursor.getInt(3));
                    report.setUsedFeed(cursor.getInt(4));

                    report.setBadEgg(cursor.getInt(5));
                    report.setGoodEgg(cursor.getInt(6));
                    reportList.add(report);
                } while (cursor.moveToNext());
            }
            Log.d("Database", "getAllReport: " + reportList.toString());
            return reportList;
        }

        public void deleteCryptoConver(Report cc) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_REPORT, KEY_ID + " = ?",
                    new String[] { String.valueOf(cc.getId()) });
            db.close();
        }
    }

