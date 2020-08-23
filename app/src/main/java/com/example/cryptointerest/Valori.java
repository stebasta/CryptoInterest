package com.example.cryptointerest;

import android.content.Intent;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.w3c.dom.Text;

import java.io.File;

public class Valori extends AppCompatActivity {

    private API_prices api;
    private TextView TXT_btc;
    private TextView TXT_xrp;
    private TextView TXT_bat;
    private TextView TXT_upusd;

    private FileUtil fu;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valori);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        //BottomBar
        //Inizializza e assegna
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Set Seleted
        bottomNavigationView.setSelectedItemId(R.id.action_valori);
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_interessi:
                        startActivity(new Intent(getApplicationContext()
                                ,Interessi.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.action_dashboard:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.action_valori:
                        return true;

                }
                return false;
            }
        });

        fu = new FileUtil();
        api = new API_prices();

        TXT_btc = findViewById(R.id.TXT_btcprezzo);
        TXT_xrp = findViewById(R.id.TXT_xrprezzo);
        TXT_bat = findViewById(R.id.TXT_batprezzo);
        TXT_upusd = findViewById(R.id.TXT_upusdprezzo);

        inizializzaPrezzi();

        mHandler.postDelayed(new Runnable(){
            public void run(){
                getBitcoin();
                getRipple();
                getBat();
                getUpusd();

                mHandler.postDelayed(this, 5000);
            }
        }, 5000);

    }

    public void inizializzaPrezzi(){
        String exBtc = fu.readFromFile("BTC", getApplicationContext());
        String exXrp = fu.readFromFile("XRP", getApplicationContext());
        String exBat = fu.readFromFile("BAT", getApplicationContext());
        String exUpusd = fu.readFromFile("UPUSD", getApplicationContext());

        api.loadBTC();
        api.loadXRP();
        api.loadBAT();
        api.loadUPUSD();

        if(exBtc=="nulla" || exBtc==""){
            getBitcoin();
        }else{
            TXT_btc.setText(" 1 BTC : " + exBtc);
        }

        if(exXrp=="nulla"|| exXrp==""){
            getRipple();
        }else{
            TXT_xrp.setText(" 1 XRP : " + exXrp);
        }

        if(exBat=="nulla"|| exBat==""){
            getBat();
        }else{
            TXT_bat.setText(" 1 BAT : " + exBat);
        }

        if(exUpusd=="nulla"|| exUpusd==""){
            getUpusd();
        }else{
            TXT_upusd.setText(" 1 UPUSD : " + exUpusd);
        }


    }

    public void getBitcoin(){
        String btcString = api.loadBTC();
        TXT_btc.setText(" 1 BTC : " + btcString);
        fu.writeToFile("BTC", btcString, getApplicationContext());
    }

    public void getRipple(){
        String xrpString = api.loadXRP();
        TXT_xrp.setText(" 1 XRP : " + xrpString);
        fu.writeToFile("XRP", xrpString, getApplicationContext());
    }

    public void getBat(){
        String batString = api.loadBAT();
        TXT_bat.setText(" 1 BAT : " + batString);
        fu.writeToFile("BAT", batString, getApplicationContext());
    }

    public void getUpusd(){
        String upusdString = api.loadUPUSD();
        TXT_upusd.setText(" 1 UPUSD : " + upusdString);
        fu.writeToFile("UPUSD", upusdString, getApplicationContext());
    }

}


