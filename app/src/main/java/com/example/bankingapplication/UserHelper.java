package com.example.bankingapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class UserHelper extends SQLiteOpenHelper {

    String TABLE_NAME = UserContract.UserEntry.TABLE_NAME;

    /** Name of the database file */
    private static final String DATABASE_NAME = "User.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.*/
    private static final int DATABASE_VERSION = 1;

    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_USER_TABLE =  "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " ("
                + UserContract.UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " INTEGER, "
                + UserContract.UserEntry.COLUMN_USER_NAME + " VARCHAR, "
                + UserContract.UserEntry.COLUMN_USER_EMAIL + " VARCHAR, "
                + UserContract.UserEntry.COLUMN_USER_IFSC_CODE + " VARCHAR, "
                + UserContract.UserEntry.COLUMN_USER_PHONE_NO + " VARCHAR, "
                + UserContract.UserEntry.COLUMN_USER_ACCOUNT_BALANCE + " INTEGER NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USER_TABLE);

        // Insert Into Table
        db.execSQL("insert into " + TABLE_NAME + " values(0001,'Sumaiya Agha', 'sumaiya2@gmail.com','3690','03323223232', 550000)");
        db.execSQL("insert into " + TABLE_NAME + " values(0088,'Suman Farhan', 'suman79@gmail.com','1897','03363223266', 14000)");
        db.execSQL("insert into " + TABLE_NAME + " values(1199,'Wali Sohaib', 'wali3@gmail.com','6256','03221552322', 8000)");
        db.execSQL("insert into " + TABLE_NAME + " values(1183,'Fazila Ali', 'fazillaa0@gmail.com','7352','03398654169', 19000)");
        db.execSQL("insert into " + TABLE_NAME + " values(2296,'Zain Ansari', 'zain46@gmail.com','7863','06678964321', 16000)");
        db.execSQL("insert into " + TABLE_NAME + " values(2247,'Ahmed Ali', 'ahmedali007@gmail.com','7070','03387954651', 87000)");
        db.execSQL("insert into " + TABLE_NAME + " values(3391,'Kausar Ahmed', 'Kausar@gmail.com','8902','03398612316', 9080)");
        db.execSQL("insert into " + TABLE_NAME + " values(3306,'Fazeen Khan', 'fazeen6@gmail.com','4522','03333456232', 8908)");
        db.execSQL("insert into " + TABLE_NAME + " values(4409,'Shaista Kanwal', 'skanwal@gmail.com','8664','032112883536', 110450)");
        db.execSQL("insert into " + TABLE_NAME + " values(4493,'Muhammd Dawoood', 'dawoodkhan@gmail.com','8799','02213661635', 10000)");
        db.execSQL("insert into " + TABLE_NAME + " values(5520,'Daniyal zafar', 'daniyal@gmail.com','9188','9019644478', 7000)");
        db.execSQL("insert into " + TABLE_NAME + " values(5519,'Arishe Faraz', 'arishaesarfaraz@gmail.com','3694','03363223232', 12200)");
        db.execSQL("insert into " + TABLE_NAME + " values(6636,'Sarfaraz Ahmef', 'sarfaraz@gmail.com','8711','09925664565', 99000)");
        db.execSQL("insert into " + TABLE_NAME + " values(6615,'Bisma Zakir', 'bismaz66@gmail.com','9000','03323112121', 9800)");
        db.execSQL("insert into " + TABLE_NAME + " values(7731,'Hassan Farooqui', 'hassanfarooqui@gmail.com','3532','03333332226', 9993)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + UserContract.UserEntry.TABLE_NAME, null);
        return cursor;
    }

    public Cursor readParticularData (int accountNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + UserContract.UserEntry.TABLE_NAME + " where " +
                                        UserContract.UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " = " + accountNo, null);
        return cursor;
    }

    public void updateAmount(int accountNo, int amount) {
        Log.d ("TAG", "update Amount");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update " + UserContract.UserEntry.TABLE_NAME + " set " + UserContract.UserEntry.COLUMN_USER_ACCOUNT_BALANCE + " = " + amount + " where " +
                UserContract.UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " = " + accountNo);
    }
}