package com.rizzoli.fatturoio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.serverDatabaseModel.Fattura;
import com.rizzoli.fatturoio.serverDatabaseModel.TTTesttt;
import com.rizzoli.fatturoio.utils.VolleyUtils;

import org.json.JSONObject;

import java.util.ArrayList;


public class CercaScadenziario extends AppCompatActivity {


    EditText meseText;
    EditText settimanaText;

    ListView listMesi;
    ListView listSettimane;
    ArrayList<String> arrMesi;
    TextView textMesi;
    TextView textSettimane;
    RadioGroup group;

    RadioButton radioButton;
    Button cerca;

    Integer radioSelezione=0;  // 0=entrambe 1=entrata 2=uscita
    String stringaRadio="null";
    Integer valSuccessivo=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca_scadenziario);
        listMesi=findViewById(R.id.listMesi);
        listSettimane=findViewById(R.id.listSettimane);

        textMesi=findViewById(R.id.textMesi);
        textSettimane=findViewById(R.id.textSettimane);
        cerca=findViewById(R.id.btnCerca);


        group=(RadioGroup)findViewById(R.id.btnGroup);

        final String stringaMese=textMesi.getText().toString();
        final String stringaSettimana=textSettimane.getText().toString();

        String[] mesi=new String[]{"tutti","0","1","2","3","4","5","6","7","8","9","10","11"};
        arrMesi =new ArrayList<String>();
        for(int i=0;i<mesi.length;i++){
            arrMesi.add(mesi[i]);
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrMesi);
        listMesi.setAdapter(arrayAdapter);

        listSettimane.setAdapter(arrayAdapter);


        listMesi.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (arrMesi.get(position).contentEquals("tutti")) {
                    textMesi.setText(stringaMese);
                    valSuccessivo=null;

                } else {
                    String resultStringaMese = stringaMese + " " + arrMesi.get(position);
                    Toast.makeText(CercaScadenziario.this, arrMesi.get(position), Toast.LENGTH_SHORT).show();
                    textMesi.setText(resultStringaMese);
                    valSuccessivo=Integer.parseInt(arrMesi.get(position));

                }

                textSettimane.setText(stringaSettimana);

            }
        });

        listSettimane.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(arrMesi.get(position).contentEquals("tutti")){
                    textSettimane.setText(stringaSettimana);
                    valSuccessivo=null;


                }else {
                    String resultStringaSettimana = stringaSettimana + " " + arrMesi.get(position);
                    Toast.makeText(CercaScadenziario.this, arrMesi.get(position), Toast.LENGTH_SHORT).show();
                    textSettimane.setText(resultStringaSettimana);
                    valSuccessivo=Integer.parseInt(arrMesi.get(position));
                }
                textMesi.setText(stringaMese);

            }
        });


        cerca.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String sMese=textMesi.getText().toString();
                String sSettimana=textSettimane.getText().toString();
                Intent i=new Intent(CercaScadenziario.this,MostraScadenziario.class);


                if(sMese.length()>=17){
                    Toast.makeText(CercaScadenziario.this,"mesi : "+valSuccessivo.toString()+" "+radioSelezione, Toast.LENGTH_SHORT).show();
                    i.putExtra("mese",valSuccessivo.toString());
                    i.putExtra("settimana","null");
                    i.putExtra("radio",stringaRadio);


                }else if(sSettimana.length()>=22){
                    Toast.makeText(CercaScadenziario.this,"settimane : "+valSuccessivo.toString()+" "+radioSelezione, Toast.LENGTH_SHORT).show();
                    i.putExtra("mese","null");
                    i.putExtra("settimana",valSuccessivo.toString());
                    i.putExtra("radio",stringaRadio);
                }else{
                    Toast.makeText(CercaScadenziario.this,"niente "+radioSelezione, Toast.LENGTH_SHORT).show();
                    i.putExtra("mese","null");
                    i.putExtra("settimana","null");
                    i.putExtra("radio",stringaRadio);
                }
                // fare la nuova activity
                startActivity(i);
                finish();



            }

        });

    }

    public void checkButton(View v) {
        int radioId = group.getCheckedRadioButtonId();

        switch(radioId){
            case R.id.btnEntrambe:
                radioSelezione = 0;
                stringaRadio="null";
                break;

            case R.id.btnEntrata:
                radioSelezione=1;
                stringaRadio="true";
                break;

            case R.id.btnUscita:
                radioSelezione=2;
                stringaRadio="false";
                break;

        }
        Toast.makeText(CercaScadenziario.this, "button selezionato " + radioSelezione.toString(), Toast.LENGTH_SHORT).show();

    }
    public void getQuery() {

    }


}

