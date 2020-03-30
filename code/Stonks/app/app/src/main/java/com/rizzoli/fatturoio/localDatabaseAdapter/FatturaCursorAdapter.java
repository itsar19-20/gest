package com.rizzoli.fatturoio.localDatabaseAdapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.business.Converter;

public class FatturaCursorAdapter extends CursorAdapter {

    public FatturaCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_fattura, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        FatturaDatabaseAdapter fatturaDatabaseAdapter = new FatturaDatabaseAdapter(context);
        TextView numeroFattura = view.findViewById(R.id.li_tv_numero_fattura);
        numeroFattura.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(7))));

        TextView data = view.findViewById(R.id.li_tv_data_fattura);
        data.setText(Converter.data(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)))));

        TextView fornitoreCliente = view.findViewById(R.id.li_tv_fornitore_cliente);
        fornitoreCliente.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));

        TextView persona = view.findViewById(R.id.li_tv_persona);
        persona.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5))));

        TextView conto = view.findViewById(R.id.li_tv_conto);
        conto.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(12))));

        TextView lordo = view.findViewById(R.id.li_tv_lordo);
        lordo.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(9))));
    }

}
