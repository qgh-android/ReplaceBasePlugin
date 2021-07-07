package com.gavin.asmdemo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

public interface IBaseBroadcastContext {
    Intent registerReceiverReally(BroadcastReceiver receiver, IntentFilter filter);

    void unregisterReceiverReally(BroadcastReceiver receiver);
}
