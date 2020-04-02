package com.rizzoli.fatturoio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.serverDatabaseModel.Fattura;
import com.rizzoli.fatturoio.utils.FatturaScadenzaAdapter;
import com.rizzoli.fatturoio.utils.VolleyUtils;

import java.util.ArrayList;

public class MostraScadenziario extends AppCompatActivity {


    ListView listView;
    ArrayList<Fattura> listaFatture=new ArrayList<>();
    FatturaScadenzaAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_scadenziario);


        Intent intent= getIntent();
        String mesi = intent.getStringExtra("mese");
        String settimane=intent.getStringExtra("settimana");
        String radioButton=intent.getStringExtra("radio");

        listView =findViewById(R.id.listViewScadenziario);
        //Toast.makeText(MostraScadenziario.this, mesi, Toast.LENGTH_SHORT).show();
        //Toast.makeText(MostraScadenziario.this, settimane, Toast.LENGTH_SHORT).show();
        //Toast.makeText(MostraScadenziario.this, radioButton, Toast.LENGTH_SHORT).show();

        searchFatture(mesi,settimane,radioButton);



    }


    public void searchFatture(String mesi ,String settimane, String radioButton) {
        StringRequest request =new StringRequest(

                Request.Method.GET,

                //VolleyUtils.url("scadenza?user=58&numMesi=null&numSettimane=null&entrataUscita=null"),
                VolleyUtils.url("scadenza?user=58&numMesi="+mesi+"&numSettimane="+settimane+"&entrataUscita="+radioButton),

                response -> {
                    // Toast.makeText(CercaScadenziario.this, "prima di try", Toast.LENGTH_SHORT).show();
                    try {

                        Fattura[] fatture=VolleyUtils.getGsonInstance().fromJson(response, Fattura[].class);
                        int i=0;
                         for (Fattura f:fatture) {

                             listaFatture.add(f);
                             Toast.makeText(MostraScadenziario.this, listaFatture.get(i).getNumeroFattura(), Toast.LENGTH_SHORT).show();
                                i++;
                         }

                            adapter=new FatturaScadenzaAdapter(MostraScadenziario.this,listaFatture);
                            listView.setAdapter(adapter);








                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MostraScadenziario.this, "errore nel catch", Toast.LENGTH_SHORT).show();

                    }
                    Log.e("SERVLET_RESPONSE", response.toString());
                },
                error -> {
                    Toast.makeText(MostraScadenziario.this, "errore", Toast.LENGTH_SHORT).show();

                }
        );
        VolleyUtils.getRequestQueueInstance(MostraScadenziario.this).add(request);

    }
}
