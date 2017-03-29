package com.example.zalegoadmin.lizsharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ZalegoAdmin on 3/9/2017.
 */
public class LocalStorage{
    SharedPreferences sp;
    public LocalStorage(Context c){
        sp=c.getSharedPreferences("User",0);

    }
    public void storeData(String name){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("name",name);
        editor.commit();
    }
    public String getData(){
        String name=sp.getString("name", "");
        return name;
    }
    public void clearData(){
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
        editor.commit();
    }
}
