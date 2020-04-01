package com.rizzoli.fatturoio.localDatabaseAdapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.rizzoli.fatturoio.R;

public class ArticoloCursorAdapter extends CursorAdapter {

    public ArticoloCursorAdapter(Context context, Cursor c) { super(context, c); }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_articolo, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView descrizione = view.findViewById(R.id.li_articolo_descrizione);
        descrizione.setText(cursor.getString(cursor.getColumnIndex(ArticoloDatabaseAdapter.getKeyDescrizione())));
        TextView quantita = view.findViewById(R.id.li_articolo_quantita);
        quantita.setText(cursor.getString(cursor.getColumnIndex(ArticoloDatabaseAdapter.getKeyQuantita())));
        TextView prezzo = view.findViewById(R.id.li_articolo_prezzo);
        prezzo.setText(cursor.getString(cursor.getColumnIndex(ArticoloDatabaseAdapter.getKeyPrezzo())));
        TextView parzioale = view.findViewById(R.id.li_articolo_parziale);
        parzioale.setText(cursor.getString(cursor.getColumnIndex(ArticoloDatabaseAdapter.getKeyParziale())));
    }

}
