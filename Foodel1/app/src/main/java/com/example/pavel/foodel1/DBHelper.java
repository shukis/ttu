package com.example.pavel.foodel1;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "usersDb";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_CITY = "city";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_EMAIL + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_SURNAME + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_PASSWORD + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);
        onCreate(db);

    }
}
