package com.example.cryptointerest;

import android.content.Context;
import android.util.Log;
import org.json.JSONObject;

import java.io.*;

public class FileUtil {

    String ritorno = "nulla";

    public void writeToFile(String valuta, String prezzo, Context context) {
        File file = new File(context.getFilesDir(), "prices_" + valuta +".txt");
        JSONObject json = new JSONObject();;

        try{
            json.put(valuta, prezzo);
            String userString = json.toString();

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userString);
            bufferedWriter.close();

        }catch(Exception e){
            Log.e("Exception", "Errore Json");
        }
    }

    public void writeToFileCoin(String valuta, String coins, Context context) {
        File file = new File(context.getFilesDir(), "coins_" + valuta +".txt");
        JSONObject json = new JSONObject();;

        try{
            json.put(valuta, coins);
            String userString = json.toString();

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userString);
            bufferedWriter.close();

        }catch(Exception e){
            Log.e("Exception", "Errore Json");
        }
    }

    public String readFromFile(String valuta, Context context) {
        try{
            File file = new File(context.getFilesDir(), "prices_" + valuta +".txt");

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String responce = stringBuilder.toString();

            JSONObject json = new JSONObject(responce);
            Log.e("READ", responce);
            ritorno = json.getString(valuta);
            Log.e("READ", "Fine Json " + valuta + " " +ritorno);

            return ritorno;
        }catch(Exception e){
            Log.e("Exception", "Lettura json fallita");
        }

        return "Errore";
    }

    public String readFromFileCoin(String valuta, Context context) {
        try{
            File file = new File(context.getFilesDir(), "coins_" + valuta +".txt");

            if(isFileExisting(file.getName(), context, false)==false){
                return "";
            }

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String responce = stringBuilder.toString();

            JSONObject json = new JSONObject(responce);
            Log.e("READ", responce);
            ritorno = json.getString(valuta);
            Log.e("READ", "Fine Json " + valuta + " " +ritorno);

            return ritorno;
        }catch(Exception e){
            Log.e("Exception", "Lettura json fallita");
        }

        return "Errore";
    }

    public boolean isFileExisting(String nomeFile, Context context, boolean isForPrices){
        boolean ritorno = true;

        if(isForPrices==true){
            if(!nomeFile.startsWith("prices")){
                nomeFile = "prices_" + nomeFile;
            }
        }


        if(!nomeFile.endsWith(".txt")){
            nomeFile = nomeFile + ".txt";
        }

        File file = context.getFileStreamPath(nomeFile);
        if(file == null || !file.exists()) {
            ritorno = false;
        }

        return ritorno;
    }

}
