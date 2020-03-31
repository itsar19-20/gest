package com.rizzoli.fatturoio.localDatabaseAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rizzoli.fatturoio.utils.DatabaseHelper;

public class ContoDatabaseAdapter {

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private static final String
            TABLE_CONTO = "conto",
            KEY_ID = "_id",
            KEY_NOME = "nome",
            KEY_PREFISSO = "prefisso",
            KEY_SALDO_DISPONIBILE = "saldoDisponibile",
            KEY_SALDO_CONTABILE = "saldoContabile",
            KEY_UTENTE = "utente";

    public ContoDatabaseAdapter(Context context) { this.context = context; }

    public ContoDatabaseAdapter open() {
        try {
            dbHelper = new DatabaseHelper(context);
            database = dbHelper.getWritableDatabase();
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("CONTO DB ADAPTER: ", e.toString());
        }
        return null;
    }

    public void close() { dbHelper.close(); }

    private ContentValues createContentValues(Integer _id, String nome, String prefisso, float saldoDisponibile, float saldoContabile, Integer utente) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, _id);
        values.put(KEY_NOME, nome);
        values.put(KEY_PREFISSO, prefisso);
        values.put(KEY_SALDO_DISPONIBILE, saldoDisponibile);
        values.put(KEY_SALDO_CONTABILE, saldoContabile);
        values.put(KEY_UTENTE, utente);
        return values;
    }

    public long create (Integer _id, String nome, String prefisso, float saldoDisponibile, float saldoContabile, Integer utente) {
        ContentValues values = createContentValues(_id, nome, prefisso, saldoDisponibile, saldoContabile, utente);
        return database.insertOrThrow(TABLE_CONTO, null, values);
    }

    public boolean update(Integer _id, String nome, String prefisso, float saldoDisponibile, float saldoContabile, Integer utente) {
        ContentValues updateValues = createContentValues(_id, nome, prefisso, saldoDisponibile, saldoContabile, utente);
        return database.update(TABLE_CONTO, updateValues, KEY_ID + "=" + _id, null) > 0;
    }

    public boolean exist(Integer _id) {
        try {
            Cursor cursor = database.rawQuery("SELECT " + KEY_ID + " FROM " + TABLE_CONTO + " WHERE " + KEY_ID + "=" + _id, new String[]{});
            if (cursor.moveToFirst()) return true;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public String getNome(Integer _id) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_CONTO + " WHERE " + KEY_ID + "=" + _id, new String[] {});
        if (cursor.moveToFirst()) return cursor.getString(1);
        return null;
    }



}
