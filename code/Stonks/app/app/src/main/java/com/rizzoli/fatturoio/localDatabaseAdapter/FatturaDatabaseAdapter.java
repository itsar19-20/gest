package com.rizzoli.fatturoio.localDatabaseAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rizzoli.fatturoio.utils.DatabaseHelper;

public class FatturaDatabaseAdapter {

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private static final String
            TABLE_FATTURA = "fattura",
            KEY_ID = "_id",
            KEY_DATA = "data",
            KEY_ANNO = "anno",
            KEY_SCADENZA = "scadenza",
            KEY_E_UNA_FATTURA_CLIENTE = "eUnaFatturaCliente",
            KEY_PERSONA = "persona",
            KEY_NOTA = "nota",
            KEY_NUMERO_FATTURA = "numeroFattura",
            KEY_IVA = "iva",
            KEY_LORDO = "lordo",
            KEY_PAGATA = "pagata",
            KEY_NOTA_DI_CREITO = "notaDiCredito",
            KEY_CONTO = "conto";

    public FatturaDatabaseAdapter(Context context) { this.context = context; }

    public FatturaDatabaseAdapter open() {
        try {
            dbHelper = new DatabaseHelper(context);
            database = dbHelper.getWritableDatabase();
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("FATTURA DB ADAPTER: ", e.toString());
        }
        return null;
    }

    public void close() { dbHelper.close(); }

    private ContentValues createContentValues(Integer _id, String data, Integer anno, Integer scadenza, Integer eUnaFatturaCliente,
                                        Integer persona, String nota, String numeroFattura, float iva, float lordo,
                                        Integer pagata, Integer notaDiCredito, Integer conto) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, _id);
        values.put(KEY_DATA, data);
        values.put(KEY_ANNO, anno);
        values.put(KEY_SCADENZA, scadenza);
        values.put(KEY_E_UNA_FATTURA_CLIENTE, eUnaFatturaCliente);
        values.put(KEY_PERSONA, persona);
        values.put(KEY_NOTA, nota);
        values.put(KEY_NUMERO_FATTURA, numeroFattura);
        values.put(KEY_IVA, iva);
        values.put(KEY_LORDO, lordo);
        values.put(KEY_PAGATA, pagata);
        values.put(KEY_NOTA_DI_CREITO, notaDiCredito);
        values.put(KEY_CONTO, conto);
        return values;
    }

    public long create (Integer _id, String data, Integer anno, Integer scadenza, Integer eUnaFatturaCliente,
                        Integer persona, String nota, String numeroFattura, float iva, float lordo,
                        Integer pagata, Integer notaDiCredito, Integer conto) {
        ContentValues values = createContentValues(_id, data, anno, scadenza, eUnaFatturaCliente,
                persona, nota, numeroFattura, iva, lordo, pagata, notaDiCredito, conto);
        return database.insertOrThrow(TABLE_FATTURA, null, values);
    }

    public Cursor fetchAll() {
        return database.query(TABLE_FATTURA, new String[] {KEY_ID, KEY_DATA, KEY_ANNO, KEY_SCADENZA, KEY_E_UNA_FATTURA_CLIENTE,
        KEY_PERSONA, KEY_NOTA, KEY_NUMERO_FATTURA, KEY_IVA, KEY_LORDO, KEY_PAGATA, KEY_NOTA_DI_CREITO, KEY_CONTO}, null, null, null, null,null);
    }

    public Integer getMaxId() {
        Cursor cursor = database.rawQuery("SELECT max(" + KEY_ID + ") FROM " + TABLE_FATTURA, new String[] {});
        Integer max = 0;
        if (cursor.moveToFirst()) max = cursor.getInt(0);
        return max;
    }

    public Context getContext() {
        return context;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public DatabaseHelper getDbHelper() {
        return dbHelper;
    }

    public static String getTableFattura() {
        return TABLE_FATTURA;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyData() {
        return KEY_DATA;
    }

    public static String getKeyAnno() {
        return KEY_ANNO;
    }

    public static String getKeyScadenza() {
        return KEY_SCADENZA;
    }

    public static String getKeyEUnaFatturaCliente() {
        return KEY_E_UNA_FATTURA_CLIENTE;
    }

    public static String getKeyPersona() {
        return KEY_PERSONA;
    }

    public static String getKeyNota() {
        return KEY_NOTA;
    }

    public static String getKeyNumeroFattura() {
        return KEY_NUMERO_FATTURA;
    }

    public static String getKeyIva() {
        return KEY_IVA;
    }

    public static String getKeyLordo() {
        return KEY_LORDO;
    }

    public static String getKeyPagata() {
        return KEY_PAGATA;
    }

    public static String getKeyNotaDiCreito() {
        return KEY_NOTA_DI_CREITO;
    }

    public static String getKeyConto() {
        return KEY_CONTO;
    }
}