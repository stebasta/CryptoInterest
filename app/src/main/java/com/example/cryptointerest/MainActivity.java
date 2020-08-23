package com.example.cryptointerest;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FileUtil fu = new FileUtil();
    private ImageButton BTN_settings;
    private TextView TXT_coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        //Inizializza e assegna
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home seleted
        bottomNavigationView.setSelectedItemId(R.id.action_dashboard);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_interessi:
                        startActivity(new Intent(getApplicationContext(), Interessi.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.action_dashboard:
                        return true;

                    case R.id.action_valori:
                        startActivity(new Intent(getApplicationContext(), Valori.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        BTN_settings = findViewById(R.id.BTN_settings);
        TXT_coins = findViewById(R.id.TXT_coin);
        TXT_coins.setText("");

        String coin = fu.readFromFileCoin("BTC", getApplicationContext());
        String valore = fu.readFromFile("BTC", getApplicationContext());
        if(coin=="" || coin==null){
            coin = "0";
        }
        String totale = Double.parseDouble(coin)*Double.parseDouble(valore.replace(" EUR", ""))+"";
        TXT_coins.append("BTC: " + totale + "\n");

        coin = fu.readFromFileCoin("XRP", getApplicationContext());
        valore = fu.readFromFile("XRP", getApplicationContext());
        if(coin=="" || coin==null){
            coin = "0";
        }
        totale = Double.parseDouble(coin)*Double.parseDouble(valore.replace(" EUR", ""))+"";
        TXT_coins.append("XRP: " + totale + "\n");

        coin = fu.readFromFileCoin("BAT", getApplicationContext());
        valore = fu.readFromFile("BAT", getApplicationContext());
        if(coin=="" || coin==null){
            coin = "0";
        }
        totale = Double.parseDouble(coin)*Double.parseDouble(valore.replace(" EUR", ""))+"";
        TXT_coins.append("BAT: " + totale + "\n");

        coin = fu.readFromFileCoin("UPUSD", getApplicationContext());
        valore = fu.readFromFile("UPUSD", getApplicationContext());
        if(coin=="" || coin==null){
            coin = "0";
        }
        totale = Double.parseDouble(coin)*Double.parseDouble(valore.replace(" EUR", ""))+"";
        TXT_coins.append("UPUSD: " + totale + "\n");

        BTN_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Settings.class));
            }
        });
    }
}
