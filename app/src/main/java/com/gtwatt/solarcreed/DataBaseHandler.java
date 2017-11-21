package com.gtwatt.solarcreed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.gtwatt.solarcreed.model.Expense;
import com.gtwatt.solarcreed.model.Report;
import com.gtwatt.solarcreed.model.Vaccine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gtwatt on 11/5/17.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        Context mcontext;

        private static final String DATABASE_NAME = "ReportDb";

        private static final String TABLE_REPORT = "reportTable";
         private static final String TABLE_EXPENSE = "expenseTable";
    private static final String TABLE_VACCINE = "vaccineTable";



    private static final String KEY_ID = "id";
        private static final String KEY_BAD_EGG = "badegg";
        private static final String KEY_GOOD_EGG = "goodegg";
        private static final String KEY_USED_FEED = "usedfeed";
        private static final String KEY_NEW_FEED = "newfeed";
        private static final String KEY_MORTALITY = "mortality";
        private static final String KEY_SICK_BIRD = "sickbird";
    private static final String KEY_DATE = "date";

    private static final String KEY_EXPENSE_FEED = "feedexpense";
    private static final String KEY_EXPENSE_BIRD = "birdexpense";
    private static final String KEY_EXPENSE_EGG = "eggexpense";

    private static final String KEY_DETAILS = "vaccineDetails";
    private static final String KEY_VACCINE_DATE = "vaccineDate";
    private static final String KEY_BIRDS_NUMBER = "birdsNumber";
    private static final String KEY_VACCINE_TYPE = "vaccineType";
    private static final String KEY_PEN = "penNumber";


    public DataBaseHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mcontext = context;
        }

        // Creating Tables
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_RECORD_TABLE = "CREATE TABLE " + TABLE_REPORT + "("
                    + KEY_ID + " INTEGER IDENTITY(1,1) PRIMARY KEY ," + KEY_MORTALITY + " INTERGER,"
                    + KEY_SICK_BIRD + " INTERGER," + KEY_NEW_FEED + " INTERGER," + KEY_USED_FEED +
                    " INTERGER," + KEY_PEN + " INTERGER" + KEY_BAD_EGG + " INTERGER,"+ KEY_DATE + " STRING,"+ KEY_GOOD_EGG + " INTERGER" +")";

            String CREATE_EXPENSE_TABLE = "CREATE TABLE " + TABLE_EXPENSE + "("
                    + KEY_ID + " INTEGER IDENTITY(1,1) PRIMARY KEY ," + KEY_EXPENSE_FEED + " INTERGER," + KEY_EXPENSE_BIRD +
                    " INTERGER," + KEY_EXPENSE_EGG + " INTERGER,"+ KEY_DATE + " STRING" +")";

            String CREATE_VACCINATION_TABLE = "CREATE TABLE " + TABLE_VACCINE + "("
                    + KEY_ID + " INTEGER IDENTITY(1,1) PRIMARY KEY ," + KEY_VACCINE_TYPE + " STRING," + KEY_VACCINE_DATE +
                    " STRING," + KEY_BIRDS_NUMBER + " INTERGER,"+ KEY_DETAILS + " STRING" +")";

            db.execSQL(CREATE_RECORD_TABLE);
            db.execSQL(CREATE_EXPENSE_TABLE);
            db.execSQL(CREATE_VACCINATION_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_VACCINE);



            onCreate(db);
        }

        public void addReport(Report report) {
            SQLiteDatabase db = this.getWritableDatabase();
            Report checkedreport = checkReport(report);
            if (checkedreport != null){
                updateReport(report);
            }else {

                ContentValues values = new ContentValues();
                values.put(KEY_BAD_EGG, report.getBadEgg());
                values.put(KEY_GOOD_EGG, report.getGoodEgg());

                values.put(KEY_USED_FEED, report.getUsedFeed());
                values.put(KEY_NEW_FEED, report.getNewFeed());


                values.put(KEY_MORTALITY, report.getMortality());
                values.put(KEY_SICK_BIRD, report.getSickBirds());

                values.put(KEY_DATE, report.getDate());
                values.put(KEY_PEN, report.getPen());

                db.insert(TABLE_REPORT, null, values);
                db.close();
                ShowAlert.showAlert(mcontext, "Today Report Saved");

                Toast.makeText(mcontext, "saving report", Toast.LENGTH_SHORT).show();
            }

        }

    public void addExpense(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();
        Expense expense1 = getExpense(expense.getDate());
        if (expense1 != null){
            updateExpense(expense);
        }else {

            ContentValues values = new ContentValues();
            values.put(KEY_EXPENSE_FEED, expense.getFeedExpense());
            values.put(KEY_EXPENSE_BIRD, expense.getBirdExpense());

            values.put(KEY_EXPENSE_EGG, expense.getMiscExpense());
            values.put(KEY_DATE, expense.getDate());

            db.insert(TABLE_EXPENSE, null, values);
            db.close();
            ShowAlert.showAlert(mcontext, "Expense Saved");

            Toast.makeText(mcontext, "saving Expense", Toast.LENGTH_SHORT).show();
        }

    }

    public void addVaccine(Vaccine vaccine) {
        SQLiteDatabase db = this.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(KEY_VACCINE_DATE, vaccine.getDate());
            values.put(KEY_VACCINE_TYPE, vaccine.getType());

            values.put(KEY_BIRDS_NUMBER, vaccine.getNumberBirds());
            values.put(KEY_DETAILS, vaccine.getDetail());

            db.insert(TABLE_VACCINE, null, values);
            db.close();
            ShowAlert.showAlert(mcontext, "Vaccination Scheduled");

        Toast.makeText(mcontext, "saving Expense", Toast.LENGTH_SHORT).show();

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
                    report.setId((cursor.getInt(0)));
                    report.setMortality(cursor.getInt(1));
                    report.setSickBirds(cursor.getInt(2));
                    report.setNewFeed(cursor.getInt(3));
                    report.setUsedFeed(cursor.getInt(4));
                    report.setBadEgg(cursor.getInt(5));
                    report.setGoodEgg(cursor.getInt(7));
                    report.setDate(cursor.getString(6));
                    reportList.add(report);
                } while (cursor.moveToNext());
            }
            Log.e("Database", "getAllReport: " + reportList.toString());
            return reportList;
        }

    public List<Expense> getAllExpense() {
        List<Expense> expenseList = new ArrayList<Expense>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EXPENSE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Expense expense = new Expense();
                expense.setId(cursor.getInt(0));
                expense.setBirdExpense(cursor.getInt(3));
                expense.setMiscExpense(cursor.getInt(2));
                expense.setFeedExpense(cursor.getInt(1));
                expense.setDate((cursor.getString(4)));

                expenseList.add(expense);
            } while (cursor.moveToNext());
        }
        Log.e("Database", "getAllReport: " + expenseList.toString());
        return expenseList;
    }

    public List<Vaccine> getAllVaccine() {
        List<Vaccine> vaccineList = new ArrayList<Vaccine>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VACCINE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Vaccine vaccine = new Vaccine();
                vaccine.setType(cursor.getString(1));
                vaccine.setDate(cursor.getString(2));
                vaccine.setNumberBirds(cursor.getInt(3));
                vaccine.setDetail((cursor.getString(4)));

                vaccineList.add(vaccine);
            } while (cursor.moveToNext());
        }
        Log.e("Database", "getAllReport: " + vaccineList.toString());
        return vaccineList;
    }


        public void deleteCryptoConver(Report cc) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_REPORT, KEY_ID + " = ?",
                    new String[] { String.valueOf(cc.getId()) });
            db.close();
        }

        public Report checkReport(Report rep){
            String selectQuery = " SELECT * FROM " + TABLE_REPORT + " WHERE " + KEY_DATE +" = ";
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.query(TABLE_REPORT, new String[] { KEY_ID,KEY_MORTALITY, KEY_SICK_BIRD,KEY_NEW_FEED, KEY_USED_FEED,
                            KEY_GOOD_EGG, KEY_BAD_EGG, KEY_DATE }, KEY_DATE + "=?",
                    new String[] { String.valueOf(rep.getDate()) }, null, null, null, null);


            if (cursor.moveToFirst()) {
                Report report = new Report();
                do {
                    report.setId(cursor.getInt(0));
                    report.setMortality(cursor.getInt(1));
                    report.setSickBirds(cursor.getInt(2));
                    report.setNewFeed(cursor.getInt(3));
                    report.setUsedFeed(cursor.getInt(4));
                    report.setBadEgg(cursor.getInt(5));
                    report.setGoodEgg(cursor.getInt(6));
                    report.setDate(cursor.getString(7));
                } while (cursor.moveToNext());
                return  report;

            }
            return null;
        }

    public Report getAllReportByPen(int pen){
        String selectQuery = " SELECT * FROM " + TABLE_REPORT + " WHERE " + KEY_PEN +" = ";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_REPORT, new String[] { KEY_ID,KEY_MORTALITY, KEY_SICK_BIRD,KEY_NEW_FEED, KEY_USED_FEED,
                        KEY_GOOD_EGG, KEY_BAD_EGG, KEY_DATE , KEY_PEN}, KEY_PEN + "=?",
                new String[] { String.valueOf(pen) }, null, null, null, null);


        if (cursor.moveToFirst()) {
            Report report = new Report();
            do {
                report.setId(cursor.getInt(0));
                report.setMortality(cursor.getInt(1));
                report.setSickBirds(cursor.getInt(2));
                report.setNewFeed(cursor.getInt(3));
                report.setUsedFeed(cursor.getInt(4));
                report.setBadEgg(cursor.getInt(5));
                report.setGoodEgg(cursor.getInt(6));
                report.setDate(cursor.getString(7));
                report.setPen(cursor.getInt(7));

            } while (cursor.moveToNext());
            return  report;

        }
        return null;
    }

    public int updateReport(Report report) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BAD_EGG, report.getBadEgg());
        values.put(KEY_GOOD_EGG, report.getGoodEgg());

        values.put(KEY_USED_FEED, report.getUsedFeed());
        values.put(KEY_NEW_FEED, report.getNewFeed());


        values.put(KEY_MORTALITY, report.getMortality());
        values.put(KEY_SICK_BIRD, report.getSickBirds());

        values.put(KEY_DATE, report.getDate());
        ShowAlert.showAlert(mcontext, "Today's Report Updated ");

        Toast.makeText(mcontext, "updated report for today", Toast.LENGTH_SHORT).show();

        // updating row
        return db.update(TABLE_REPORT, values, KEY_DATE + " = ?",
                new String[] { String.valueOf(report.getDate()) });
    }

    public int updateExpense(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EXPENSE_FEED, expense.getFeedExpense());
        values.put(KEY_EXPENSE_BIRD, expense.getBirdExpense());

        values.put(KEY_EXPENSE_EGG, expense.getMiscExpense());
        values.put(KEY_DATE, expense.getDate());
        ShowAlert.showAlert(mcontext, "Today's Expense  Updated");

        Toast.makeText(mcontext, "updated report for today", Toast.LENGTH_SHORT).show();

        // updating row
        return db.update(TABLE_EXPENSE, values, KEY_DATE + " = ?",
                new String[] { String.valueOf(expense.getDate()) });
    }

    public Report getReport(String date){
        String selectQuery = " SELECT * FROM " + TABLE_REPORT + " WHERE " + KEY_DATE +" = ";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_REPORT, new String[] { KEY_ID,KEY_MORTALITY, KEY_SICK_BIRD,KEY_NEW_FEED, KEY_USED_FEED,
                        KEY_GOOD_EGG, KEY_BAD_EGG, KEY_DATE }, KEY_DATE + "=?",
                new String[] { String.valueOf(date) }, null, null, null, null);


        if (cursor.moveToFirst()) {
            Report report = new Report();
            do {
                report.setId(cursor.getInt(0));
                report.setMortality(cursor.getInt(1));
                report.setSickBirds(cursor.getInt(2));
                report.setNewFeed(cursor.getInt(3));
                report.setUsedFeed(cursor.getInt(4));
                report.setBadEgg(cursor.getInt(5));
                report.setGoodEgg(cursor.getInt(6));
                report.setDate(cursor.getString(7));
            } while (cursor.moveToNext());
            return  report;

        }
        return null;
    }


    public Expense getExpense(String date){
        String selectQuery = " SELECT * FROM " + TABLE_EXPENSE + " WHERE " + KEY_DATE +" = ";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_EXPENSE, new String[] { KEY_ID,KEY_EXPENSE_BIRD, KEY_EXPENSE_EGG,KEY_EXPENSE_FEED }, KEY_DATE + "=?",
                new String[] { String.valueOf(date) }, null, null, null, null);


        if (cursor.moveToFirst()) {
            Expense expense = new Expense();
            do {
                expense.setId(cursor.getInt(0));
                expense.setBirdExpense(cursor.getInt(3));
                expense.setMiscExpense(cursor.getInt(2));
                expense.setFeedExpense(cursor.getInt(1));
            } while (cursor.moveToNext());
            return  expense;

        }
        return null;
    }

    public Vaccine getVaccine(String date){
        String selectQuery = " SELECT * FROM " + TABLE_VACCINE + " WHERE " + KEY_DATE +" = ";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_VACCINE, new String[] { KEY_ID,KEY_VACCINE_TYPE, KEY_DETAILS,KEY_VACCINE_DATE, KEY_BIRDS_NUMBER }, KEY_DATE + "=?",
                new String[] { String.valueOf(date) }, null, null, null, null);


        if (cursor.moveToFirst()) {
            Vaccine vaccine = new Vaccine();
            do {
                vaccine.setType(cursor.getString(3));
                vaccine.setDate(cursor.getString(2));
                vaccine.setNumberBirds(cursor.getInt(1));
                vaccine.setDetail((cursor.getString(4)));
            } while (cursor.moveToNext());
            return  vaccine;

        }
        return null;
    }
}

