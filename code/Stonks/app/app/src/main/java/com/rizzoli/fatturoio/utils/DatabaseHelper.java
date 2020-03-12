package com.rizzoli.fatturoio.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String
            DATABASE_NAME = "localDatabase.db",

            CREATE_TABLE_FATTURA = "CREATE TABLE fattura ("
            		+ "_id integer primary key autoincrement, "
            		+ "data text not null, "
            		+ "scadenza integer not null, "
            		// + "e_una_fattura_cliente boolean not null, "
            		+ "persona integer not null, "
            		+ "numero_fattura text not null, "
            		+ "iva float not null, "
            		+ "lordo float not null, "
            		// + "pagata boolean not null, "
            		+ "conto integer not null",
            CREATE_TABLE_ARTICOLO = "CREATE TABLE articolo ("
            		+ "_id integer primary key autoincrement, "
            		+ "descrizione text not null, "
            		+ "quantita float not null, "
            		+ "prezzo float not null, "
            		+ "parziale float not null, "
            		+ "fattura integer not null)",
            CREATE_TABLE_PERSONA = "CREATE TABLE persona ("
            		+ "_id integer primary key autoincrement, "
            		+ "nome text not null, "
            		+ "cognome text not null, "
            		+ "pIVA text not null, "
            		+ "mail text not null, "
            		+ "indirizzo text not null, "
            		+ "telefono text not null, "
            		+ "autore integer not null)",
            CREATE_TABLE_USER = "CREATE TABLE user ( "
                    + "_id integer primary key autoincrement,"
                    + "username text not null, "
                    + "password text not null,"
                    + "dataOraUltimoLogin text not null)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FATTURA);
        db.execSQL(CREATE_TABLE_ARTICOLO);
        db.execSQL(CREATE_TABLE_PERSONA);
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS fattura");
        db.execSQL("DROP TABLE IF EXISTS articolo");
        db.execSQL("DROP TABLE IF EXISTS persona");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
}
