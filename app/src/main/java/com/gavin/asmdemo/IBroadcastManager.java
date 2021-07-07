package com.gavin.asmdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

interface IBroadcastManager {
    void init(Context context);

    Intent registerReceiver(Context context, BroadcastReceiver receiver, IntentFilter filter);

    void unregisterReceiver(Context context, BroadcastReceiver receiver);

    void transformReceiver(Context context, Intent intent);


}
