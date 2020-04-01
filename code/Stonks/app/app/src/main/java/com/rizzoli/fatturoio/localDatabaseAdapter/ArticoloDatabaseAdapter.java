package com.rizzoli.fatturoio.localDatabaseAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rizzoli.fatturoio.utils.DatabaseHelper;

public class ArticoloDatabaseAdapter {

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private static final String
            TABLE_ARTICOLO = "articolo",
            KEY_ID = "_id",
            KEY_DESCRIZIONE = "descrizione",
            KEY_QUANTITA = "quantita",
            KEY_PREZZO = "prezzo",
            KEY_PARZIALE = "parziale",
            KEY_NUMERO_FATTURA = "fattura";

    public ArticoloDatabaseAdapter(Context context) { this.context = context; }

    public ArticoloDatabaseAdapter open() {
        try {
            dbHelper = new DatabaseHelper(context);
            database = dbHelper.getWritableDatabase();
            return this;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public void close() { dbHelper.close(); }

    private ContentValues createContentValues(Integer _id, String descrizione, float qunatita, float prezzo, float parziale, Integer fatturaId) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, _id);
        values.put(KEY_DESCRIZIONE, descrizione);
        values.put(KEY_QUANTITA, qunatita);
        values.put(KEY_PREZZO, prezzo);
        values.put(KEY_PARZIALE, parziale);
        values.put(KEY_NUMERO_FATTURA, fatturaId);
        return values;
    }

    public long crate(Integer _id, String descrizione, float qunatita, float prezzo, float parziale, Integer fatturaId) {
        ContentValues values = createContentValues(_id, descrizione, qunatita, prezzo, parziale, fatturaId);
        return database.insertOrThrow(TABLE_ARTICOLO, null, values);
    }

    public Cursor fetchByFatturaId(Integer fatturaId) {
        // Cursor cursor = database.query(TABLE_ARTICOLO, new String[] { KEY_ID,KEY_DESCRIZIONE, KEY_QUANTITA, KEY_PREZZO, KEY_PARZIALE, KEY_NUMERO_FATTURA }, null, null, null, null, null);
        // Cursor cursor = database.query(true, TABLE_ARTICOLO, new String[] {KEY_ID,KEY_DESCRIZIONE, KEY_QUANTITA, KEY_PREZZO, KEY_PARZIALE, KEY_NUMERO_FATTURA}, KEY_NUMERO_FATTURA + "like '%" + fatturaId + "%'", null, null, null, null, null);
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_ARTICOLO + " WHERE " + KEY_NUMERO_FATTURA + " = " + fatturaId, new String[] {});
        return cursor;
    }

    public static String getTableArticolo() {
        return TABLE_ARTICOLO;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyDescrizione() {
        return KEY_DESCRIZIONE;
    }

    public static String getKeyQuantita() {
        return KEY_QUANTITA;
    }

    public static String getKeyPrezzo() {
        return KEY_PREZZO;
    }

    public static String getKeyParziale() {
        return KEY_PARZIALE;
    }

    public static String getKeyNumeroFattura() {
        return KEY_NUMERO_FATTURA;
    }
}
