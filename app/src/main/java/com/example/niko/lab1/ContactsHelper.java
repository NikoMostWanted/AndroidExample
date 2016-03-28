package com.example.niko.lab1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by niko on 26/03/16.
 */
public class ContactsHelper extends SQLiteOpenHelper implements BaseColumns {
    public static final String DATABASE_NAME = "contacts.db";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE = "contacts";
    public static final String NAME_COLUMN = "name";
    public static final String PHONE_COLUMN = "phone";
    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + NAME_COLUMN
            + " text not null, " + PHONE_COLUMN + " integer); ";

    ContactsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ContactsHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "Update  " + oldVersion + " => " + newVersion);
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
}
