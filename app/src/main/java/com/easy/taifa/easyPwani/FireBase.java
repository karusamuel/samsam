package com.easy.taifa.easyPwani;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;


public class FireBase extends Application{
    FirebaseDatabase database;

    public void onCreate(){
        super.onCreate();
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);



    }
}
