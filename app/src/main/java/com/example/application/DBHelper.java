package com.example.application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CursDB";
    public static final String TABLE_USERS = "UsersTB";
    public static final String TABLE_NOTES = "NotesTB";

    public static final String KEY_LOGIN = "Login";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";

    public static final String KEY_LOGINS = "Login";
    public static final String KEY_NOTE = "Note";
    public static final String STATUS = "Status";
    public static final String DATE = "Date";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS + "( " + KEY_LOGIN + " text primary key, " + EMAIL + " text, " + PASSWORD + " text"+ ")");
        db.execSQL("create table " + TABLE_NOTES + "("+ KEY_LOGINS + " text, " + KEY_NOTE + " text, " + STATUS + " text, " + DATE + " text primary key" + " ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        onCreate(db);

    }
}
