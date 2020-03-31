package com.rizzoli.fatturoio.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String
            DATABASE_NAME = "localDatabase.db",

            CREATE_TABLE_FATTURA = "CREATE TABLE fattura ("
            		+ "_id integer primary key autoincrement, "
            		+ "data text not null, "
					+ "anno integer not null, "
            		+ "scadenza integer not null, "
            		+ "eUnaFatturaCliente integer not null, "
            		+ "persona integer not null, "
					+ "nota text not null, "
            		+ "numeroFattura text not null, "
            		+ "iva float not null, "
            		+ "lordo float not null, "
            		+ "pagata integer not null, "
					+ "notaDiCredito integer not null, "
					+ "conto integer not null, "
					+ "numeroArticoli integer NOT NULL, "
					+ "FOREIGN KEY (persona) REFERENCES persona (_id),"
					+ "FOREIGN KEY (conto) REFERENCES conto (_id)"
					+ ")",
            CREATE_TABLE_ARTICOLO = "CREATE TABLE articolo ("
            		+ "_id integer primary key autoincrement, "
            		+ "descrizione text not null, "
            		+ "quantita float not null, "
            		+ "prezzo float not null, "
            		+ "parziale float not null, "
            		+ "fattura integer not null,"
					+ "FOREIGN KEY (fattura) REFERENCES fattura (_id)"
					+ ")",
            CREATE_TABLE_PERSONA = "CREATE TABLE persona ("
            		+ "_id integer primary key autoincrement, "
            		+ "nome text not null, "
            		+ "cognome text not null, "
            		+ "pIVA text not null, "
            		+ "mail text not null, "
            		+ "indirizzo text not null, "
            		+ "telefono text not null, "
            		+ "autore integer not null,"
					+ "eliminabile integer NOT NULL"
					+ ")",
            CREATE_TABLE_USER = "CREATE TABLE user ( "
                    + "_id integer primary key autoincrement,"
                    + "username text not null, "
                    + "password text not null,"
                    + "dataOraUltimoLogin text not null,"
					+ "FOREIGN KEY (_id) REFERENCES persona (_id)"
					+ ")",
			CREATE_TABLE_CONTO = "CREATE TABLE conto ("
					+ "_id integer PRIMARY KEY AUTOINCREMENT,"
					+ "nome texit NOT NULL,"
					+ "prefisso text NOT NULL,"
					+ "saldoDisponibile float NOT NULL,"
					+ "saldoContabile float NOT NULL,"
					+ "utente integer NOT NULL,"
					+ "FOREIGN KEY (utente) REFERENCES user (_id)"
					+ ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FATTURA);
        db.execSQL(CREATE_TABLE_ARTICOLO);
        db.execSQL(CREATE_TABLE_PERSONA);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CONTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS fattura");
        db.execSQL("DROP TABLE IF EXISTS articolo");
        db.execSQL("DROP TABLE IF EXISTS persona");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS conto");
        onCreate(db);
    }
}
