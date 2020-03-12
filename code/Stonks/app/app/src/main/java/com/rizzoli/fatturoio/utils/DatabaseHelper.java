package com.rizzoli.fatturoio.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String
            DATABASE_NAME = "localDatabase.db",

            CREATE_TABLE_FATTURA = "CREATE TABLE fattura (" +
                    "_id integer primary key autoincrement, " +
                    "note text not null," +
                    "lordo float not null)",
            CREATE_TABLE_ARTICOLO = "CREATE TABLE articolo ("
            		+ "_id integer primary key autoincrement, "
            		+ "descrizione text no"
            		+ "quantita",
            CREATE_TABLE_PERSONA = "",
            CREATE_TABLE_USER = "CREATE TABLE user ( " +
                    "_id integer primary key autoincrement )";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FATTURA);
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS fattura");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
}
