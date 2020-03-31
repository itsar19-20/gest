package com.rizzoli.fatturoio.localDatabaseAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rizzoli.fatturoio.utils.DatabaseHelper;

public class PeronaDatabaseAdapter {

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private static final String
            TABLE_PERSONA = "persona",
            KEY_ID = "_id",
            KEY_NOME = "nome",
            KEY_COGNOME = "cognome",
            KEY_PARTITA_IVA = "pIVA",
            KEY_MAIL = "mail",
            KEY_INDIRIZZO = "indirizzo",
            KEY_TELEFONO = "telefono",
            KEY_AUTORE = "autore",
            KEY_ELEIMINABILE = "eliminabile";

    public PeronaDatabaseAdapter(Context context) { this.context = context; }

    public PeronaDatabaseAdapter open() {
        try {
            dbHelper = new DatabaseHelper(context);
            database = dbHelper.getWritableDatabase();
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("PERSONA DB ADAPTER: ", e.toString());
        }
        return null;
    }

    public void close() { dbHelper.close(); }

    private ContentValues createContentValues(Integer _id, String nome, String cognome, String pIVA, String mail, String indirizzo, String telefono, Integer autore, Integer eleiminabile) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, _id);
        values.put(KEY_NOME, nome);
        values.put(KEY_COGNOME, cognome);
        values.put(KEY_PARTITA_IVA, pIVA);
        values.put(KEY_MAIL, mail);
        values.put(KEY_INDIRIZZO, indirizzo);
        values.put(KEY_TELEFONO, telefono);
        values.put(KEY_AUTORE, autore);
        values.put(KEY_ELEIMINABILE, eleiminabile);
        return values;
    }

    public long create (Integer _id, String nome, String cognome, String pIVA, String mail, String indirizzo, String telefono, Integer autore, Integer eleiminabile) {
        ContentValues values = createContentValues(_id, nome, cognome, pIVA, mail, indirizzo, telefono, autore, eleiminabile);
        return database.insertOrThrow(TABLE_PERSONA, null, values);
    }

    public boolean update(Integer _id, String nome, String cognome, String pIVA, String mail, String indirizzo, String telefono, Integer autore, Integer eleiminabile) {
        ContentValues updateValues = createContentValues(_id, nome, cognome, pIVA, mail, indirizzo, telefono, autore, eleiminabile);
        return database.update(TABLE_PERSONA, updateValues, KEY_ID + "=" + _id, null) > 0;
    }

    public boolean exist(Integer _id) {
        try {
            Cursor cursor = database.rawQuery("SELECT " + KEY_ID + " FROM " + TABLE_PERSONA + " WHERE " + KEY_ID + "=" + _id, new String[]{});
            if (cursor.moveToFirst()) return true;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public String getNomeCognome(Integer _id) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_PERSONA + " WHERE " + KEY_ID + "=" + _id, new String[] {});
        if (cursor.moveToFirst()) return cursor.getString(1) + " " + cursor.getString(2);
        return null;
    }

    public static String getTablePersona() {
        return TABLE_PERSONA;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyNome() {
        return KEY_NOME;
    }

    public static String getKeyCognome() {
        return KEY_COGNOME;
    }

    public static String getKeyPartitaIva() {
        return KEY_PARTITA_IVA;
    }

    public static String getKeyMail() {
        return KEY_MAIL;
    }

    public static String getKeyIndirizzo() {
        return KEY_INDIRIZZO;
    }

    public static String getKeyTelefono() {
        return KEY_TELEFONO;
    }

    public static String getKeyAutore() {
        return KEY_AUTORE;
    }

    public static String getKeyEleiminabile() {
        return KEY_ELEIMINABILE;
    }
}
