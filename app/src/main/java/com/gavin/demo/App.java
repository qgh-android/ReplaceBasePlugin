package com.gavin.demo;

import android.app.Application;
import android.util.Log;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("APP", "**************onCreate**************");
    }
}
