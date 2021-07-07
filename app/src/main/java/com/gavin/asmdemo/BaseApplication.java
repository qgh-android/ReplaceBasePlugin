package com.gavin.asmdemo;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;


public class BaseApplication extends Application implements IBaseBroadcastContext {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("BaseApplication", "onCreate");
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        if (BroadcastManager.getInstance().getInited()) {
            return BroadcastManager.getInstance().registerReceiver(this, receiver, filter);
        }
        return registerReceiverReally(receiver, filter);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        if (BroadcastManager.getInstance().getInited()) {
            BroadcastManager.getInstance().unregisterReceiver(this, receiver);
        } else {
            unregisterReceiverReally(receiver);
        }
    }

    @Override
    public Intent registerReceiverReally(BroadcastReceiver receiver, IntentFilter filter) {
        return super.registerReceiver(receiver, filter);
    }

    @Override
    public void unregisterReceiverReally(BroadcastReceiver receiver) {
        super.unregisterReceiver(receiver);
    }
}
