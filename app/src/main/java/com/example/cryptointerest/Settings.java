package com.example.cryptointerest;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    private Button BTN_ritorno;
    private Button BTN_scrivi;
    private Button BTN_rimuovi;
    private EditText TXT_valuta;
    private EditText TXT_num;
    private TextView TXT_errore;
    private TextView TXT_avviso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);


        BTN_ritorno = findViewById(R.id.BTN_ritorno);
        BTN_scrivi = findViewById(R.id.BTN_scrivi);
        BTN_rimuovi = findViewById(R.id.BTN_rimuovi);
        TXT_valuta = findViewById(R.id.TXT_valuta);
        TXT_num = findViewById(R.id.TXT_num);
        TXT_errore = findViewById(R.id.TXT_errore);
        TXT_avviso = findViewById(R.id.TXT_avviso);

        TXT_valuta.setEnabled(true);

        BTN_scrivi.setOnClickListener(new View.OnClickListener() {
            boolean errore = false;
            FileUtil fu = new FileUtil();

            @Override
            public void onClick(View view) {
                String valuta = TXT_valuta.getText().toString();
                if(valuta.length()!=3 && valuta.length()!=5){
                    errore = true;
                }
                valuta = correggiValuta(valuta);
                if(valuta=="ERR")
                    errore = true;

                String coin = TXT_num.getText().toString();
                if(coin.length()==0){
                    errore = true;
                }

                azzeraTXT();
                if(errore == false){
                    fu.writeToFileCoin(valuta, coin, getApplicationContext());
                    TXT_num.setText("");
                    TXT_avviso.setText("Inserimento fatto!");
                }else{
                    TXT_errore.setText("ERRORE NEGLI INSERIMENTI");
                    TXT_valuta.setText("");
                    TXT_num.setText("");
                }
            }
        });

        BTN_rimuovi.setOnClickListener(new View.OnClickListener() {
            boolean errore = false;
            FileUtil fu = new FileUtil();

            @Override
            public void onClick(View view) {
                String valuta = TXT_valuta.getText().toString();
                if(valuta.length()!=3 && valuta.length()!=5){
                    errore = true;
                }
                valuta = correggiValuta(valuta);
                if(valuta=="ERR")
                    errore = true;

                String coin = TXT_num.getText().toString();
                if(coin.length()==0){
                    errore = true;
                }

                azzeraTXT();
                if(errore == false){
                    String remCoin = fu.readFromFileCoin(valuta, getApplicationContext());
                    coin = Double.parseDouble(remCoin)-Double.parseDouble(coin)+"";
                    if(coin.startsWith("-")){
                        coin = "0";
                        TXT_errore.setText("Coin rimasti in negativo,\nimpostati a 0");
                    }
                    fu.writeToFileCoin(valuta, coin, getApplicationContext());

                    if(TXT_errore.getText().toString()==""){
                        TXT_avviso.setText("Rimozione fatta!");
                    }
                    TXT_num.setText("");

                }else{
                    TXT_errore.setText("ERRORE NEGLI INSERIMENTI");
                    TXT_valuta.setText("");
                    TXT_num.setText("");
                }
            }
        });


        BTN_ritorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    public String correggiValuta(String valuta){
        valuta = valuta.toUpperCase();

        switch(valuta){

            case "BTC": case "XRP": case "BAT": case"UPUSD":
                return valuta;

        }

        //ULTIMA LETTERA SBAGLIATA
        if(valuta.startsWith("B"))
            if(valuta.startsWith("BT"))
                return "BTC";

        if(valuta.startsWith("X"))
            if(valuta.startsWith("XR"))
                return "XRP";

        if(valuta.startsWith("B"))
            if(valuta.startsWith("BA"))
                return "BAT";

        if(valuta.startsWith("U"))
            if(valuta.startsWith("UP"))
                return "UPUSD";


        //LETTERA IN MEZZO SBAGLIATA
        if(valuta.startsWith("B"))
            if(valuta.endsWith("C"))
                return "BTC";

        if(valuta.startsWith("X"))
            if(valuta.endsWith("P"))
                return "XRP";

        if(valuta.startsWith("B"))
            if(valuta.endsWith("T"))
                return "BAT";

        if(valuta.startsWith("U"))
            if(valuta.endsWith("D"))
                return "UPUSD";

        //LETTERA INIZIALE SBAGLIATA
        if(valuta.endsWith("C"))
            if(valuta.endsWith("TC"))
                return "BTC";

        if(valuta.endsWith("P"))
            if(valuta.endsWith("RP"))
                return "XRP";

        if(valuta.endsWith("T"))
            if(valuta.endsWith("AT"))
                return "BAT";

        if(valuta.endsWith("D"))
            if(valuta.endsWith("SD"))
                return "UPUSD";

        valuta = "ERR";
        return valuta;
    }

    public void azzeraTXT(){
        TXT_avviso.setText("");
        TXT_errore.setText("");
    }
}
