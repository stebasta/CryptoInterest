package com.example.cryptointerest;

import java.io.IOException;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.*;
import org.json.JSONObject;

public class API_prices extends AppCompatActivity {
    private OkHttpClient okHttpClient;
    private String urlBase;
    private String fsym, tsyms;
    private String url;
    private String key;

    private String ritornoBtc = "";
    private String ritornoXrp = "";
    private String ritornoBat = "";
    private String ritornoUpusd = "";

    public API_prices(){
        okHttpClient= new OkHttpClient();
        urlBase = "https://min-api.cryptocompare.com/data/price?";
        fsym = "fsym=";
        tsyms = "&tsyms=";
        key = "&api_key=e3bd49258ceac4ce20c58b302f13e2242013fad8e8a0d6d9813b4759b91a6eb8";
    }

    public String loadBTC() {
        fsym = "fsym=BTC";
        tsyms = "&tsyms=EUR";
        url = urlBase + fsym + tsyms + key;

        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ritornoBtc = "API fail";
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String body = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            StringBuilder builder = new StringBuilder();
                            JSONObject jsonObject = new JSONObject(body);
                            builder.append(jsonObject.getString("EUR")).append(" EUR ").append("\n");
                            ritornoBtc = builder.toString();
                        } catch (Exception e) {

                        }
                    }
                });
            }
        });
        return ritornoBtc;
    }

    public String loadXRP() {
        fsym = "fsym=XRP";
        tsyms = "&tsyms=EUR";
        url = urlBase + fsym + tsyms + key;

        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ritornoXrp = "API fail";
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String body = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            StringBuilder builder = new StringBuilder();
                            JSONObject jsonObject = new JSONObject(body);
                            builder.append(jsonObject.getString("EUR")).append(" EUR ").append("\n");
                            ritornoXrp = builder.toString();
                        } catch (Exception e) {

                        }
                    }
                });
            }
        });
        return ritornoXrp;
    }

    public String loadBAT() {
        fsym = "fsym=BAT";
        tsyms = "&tsyms=EUR";
        url = urlBase + fsym + tsyms + key;

        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ritornoBat = "API fail";
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String body = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            StringBuilder builder = new StringBuilder();
                            JSONObject jsonObject = new JSONObject(body);
                            builder.append(jsonObject.getString("EUR")).append(" EUR ").append("\n");
                            ritornoBat = builder.toString();
                        } catch (Exception e) {

                        }
                    }
                });
            }
        });
        return ritornoBat;
    }

    public String loadUPUSD() {
        fsym = "fsym=UPUSD";
        tsyms = "&tsyms=EUR";
        url = urlBase + fsym + tsyms + key;

        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ritornoUpusd = "API fail";
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String body = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            StringBuilder builder = new StringBuilder();
                            JSONObject jsonObject = new JSONObject(body);
                            builder.append(jsonObject.getString("EUR")).append(" EUR ").append("\n");
                            ritornoUpusd = builder.toString();
                        } catch (Exception e) {

                        }
                    }
                });
            }
        });
        return ritornoUpusd;
    }
}
