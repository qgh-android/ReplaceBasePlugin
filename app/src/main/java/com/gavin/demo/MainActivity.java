package com.gavin.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import com.gavin.asmdemo.BroadcastManager;
import com.gavin.asmdemo.R;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        BroadcastManager.getInstance().init(this);
        Log.i("MainActivity", "MainActivity中的onCreate");
        TestReceiver testReceiver = new TestReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        registerReceiver(testReceiver, intentFilter);

    }

    public static class TestReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("MainActivity", "intent=" + intent.getAction());
        }
    }
}
