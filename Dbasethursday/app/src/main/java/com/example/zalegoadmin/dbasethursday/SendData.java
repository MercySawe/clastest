package com.example.zalegoadmin.dbasethursday;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZalegoAdmin on 3/20/2017.
 */
public class SendData  extends AsyncTask<Void, Void, String> {
    String name;
    String url ;
    String response;

    public SendData(String name,String url){
        this.name=name;
        this.url=url;
    }

    @Override
    protected String doInBackground(Void... params) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name", name);

        URL mainUrl;

        try {
            mainUrl = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) mainUrl.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(data));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                response= br.readLine();

            } else {
                response = "Error Posting";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    private String getPostDataString(HashMap<String, String> params){

        StringBuilder result= new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry:params.entrySet())

        {
            if (first)
                first = false;
            else
                result.append("&");

            try {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            result.append("=");
            try {
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        return result.toString();
    }
}
