package com.rizzoli.fatturoio.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.serverDatabaseModel.Fattura;

import java.util.ArrayList;

public class FatturaScadenzaAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Fattura> fatture;
    private static LayoutInflater inflater=null;

    public FatturaScadenzaAdapter(Activity context,ArrayList<Fattura> fatture){
        this.context=context;
        this.fatture=fatture;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount(){
        return fatture.size();
    }

    @Override
    public Fattura getItem(int position){
        return fatture.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView=convertView;
        itemView=inflater.inflate(R.layout.fattura_scadenza_item,parent,false);




        TextView  numeroFattura=(TextView) itemView.findViewById(R.id.numeroFattura);
        TextView  scadenzaFattura=(TextView) itemView.findViewById(R.id.scadenzaFattura);
        TextView  lordoFattura=(TextView) itemView.findViewById(R.id.lordoFattura);
        TextView  entrataUscita=(TextView) itemView.findViewById(R.id.entrataUscita);
        TextView  pagata=(TextView) itemView.findViewById(R.id.pagata);
        Fattura selectedFattura=fatture.get(position);

        numeroFattura.setText(selectedFattura.getNumeroFattura());
        scadenzaFattura.setText(String.valueOf(selectedFattura.getScadenza()));
        String stringaLordo= String.valueOf(selectedFattura.getLordo());
        lordoFattura.setText(stringaLordo);

        entrataUscita.setText( String.valueOf(selectedFattura.iseUnaFatturaCliente()));
        pagata.setText(String.valueOf(selectedFattura.isPagata()));
        numeroFattura.setText(selectedFattura.getNumeroFattura());


        return itemView;
    }


}
