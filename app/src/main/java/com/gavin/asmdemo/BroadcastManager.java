package com.gavin.asmdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public final class BroadcastManager implements IBroadcastManager {
    public static String TAG = "BroadcastManager";
    private static List<RegisterBroadcastEntity> sReceiverList = new ArrayList<>();
    private static String[] sDefUniversalAction = new String[]{
            "android.net.conn.CONNECTIVITY_CHANGE",
            "android.intent.action.PROXY_CHANGE",
            "android.intent.action.SCREEN_ON",
            "android.intent.action.SCREEN_OFF",
            "android.intent.action.USER_PRESENT",
            "android.intent.action.CLOSE_SYSTEM_DIALOGS",
            "android.intent.action.ACTION_POWER_CONNECTED",
            "android.intent.action.ACTION_POWER_DISCONNECTED",
            "android.intent.action.BATTERY_LOW",
            "android.intent.action.BATTERY_OKAY",
            "android.intent.action.TIME_TICK",
            "android.intent.action.TIME_SET",
            "android.intent.action.TIMEZONE_CHANGED",
    };
    private static String[] sDefPackageAction = new String[]{
            "android.intent.action.PACKAGE_ADDED",
            "android.intent.action.PACKAGE_REMOVED",
            "android.intent.action.PACKAGE_REPLACED",
    };

    private volatile static BroadcastManager instance;

    private BroadcastManager() {
    }

    public static BroadcastManager getInstance() {
        if (instance == null) {
            synchronized (BroadcastManager.class) {
                if (instance == null) {
                    instance = new BroadcastManager();
                }
            }
        }
        return instance;
    }

    private static AtomicBoolean isInited = new AtomicBoolean(false);

    public boolean getInited() {
        return isInited.get();
    }

    @Override
    public void init(Context context) {
        if (isInited.compareAndSet(false, true)) {
            BaseReceiver baseReceiver = new BaseReceiver();
            IntentFilter intentFilterUniversal = new IntentFilter();
            for (String action : sDefUniversalAction) {
                intentFilterUniversal.addAction(action);
            }
            ((IBaseBroadcastContext) context).registerReceiverReally(baseReceiver, intentFilterUniversal);

            BasePackageReceiver basePackageReceiver = new BasePackageReceiver();
            IntentFilter intentFilterPackage = new IntentFilter();
            for (String action : sDefPackageAction) {
                intentFilterPackage.addAction(action);
            }
            intentFilterPackage.addDataScheme("package");
            ((IBaseBroadcastContext) context).registerReceiverReally(basePackageReceiver, intentFilterPackage);

            Log.i(TAG, "baseReceiver registered end");
        } else {
            Log.e(TAG, " init again!");
        }
    }

    @Override
    public Intent registerReceiver(Context context, BroadcastReceiver receiver, IntentFilter filter) {
        if (allActionIsRegistered(filter) && receiver != null) {
            if (BuildConfig.DEBUG) {
                RegisterBroadcastEntity entity = findSavedReceiver(receiver);
                if (entity != null) {
                    Log.e(TAG, "The receiver is already registered");
                }
            }
            sReceiverList.add(new RegisterBroadcastEntity(receiver, filter));
            return null;
        }
        return ((IBaseBroadcastContext) context).registerReceiverReally(receiver, filter);
    }

    private boolean allActionIsRegistered(IntentFilter filter) {
        Iterator<String> iterator = filter.actionsIterator();
        while (iterator.hasNext()) {
            String action = iterator.next();
            if (!isContainAction(action)) {
                return false;
            }
        }
        return true;
    }

    private boolean isContainAction(String action) {
        for (String defAction : sDefUniversalAction) {
            if (action.equals(defAction)) {
                return true;
            }
        }
        for (String packageAction : sDefPackageAction) {
            if (action.equals(packageAction)) {
                return true;
            }
        }
        return false;
    }

    private RegisterBroadcastEntity findSavedReceiver(BroadcastReceiver receiver) {
        for (RegisterBroadcastEntity savedReceiver : sReceiverList) {
            if (savedReceiver.receiver == receiver) {
                return savedReceiver;
            }
        }
        return null;
    }

    @Override
    public void unregisterReceiver(Context context, BroadcastReceiver receiver) {
        if (receiver == null) {
            return;
        }
        RegisterBroadcastEntity saveReceiver = findSavedReceiver(receiver);
        if (!sReceiverList.remove(saveReceiver)) {
            if (context != null) {
                ((IBaseBroadcastContext) context).unregisterReceiverReally(receiver);
            }
        }
    }

    @Override
    public void transformReceiver(Context context, Intent intent) {
        Log.i(TAG, "transformReceiver, intent.action=" + intent.getAction());
        String targetAction = intent.getAction();
        for (RegisterBroadcastEntity savedReceiver : sReceiverList) {
            Iterator<String> actions = savedReceiver.filter.actionsIterator();
            while (actions.hasNext()) {
                String currAction = actions.next();
                if (currAction.equals(targetAction)) {
                    Log.i(TAG, "transformReceiver, receiver=" + savedReceiver.receiver.getClass().getName() + ", onReceive()");
                    savedReceiver.receiver.onReceive(context, intent);
                }
            }
        }
    }

    public static class BaseReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive");
            BroadcastManager.getInstance().transformReceiver(context, intent);
        }
    }

    public static class BasePackageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive");
            BroadcastManager.getInstance().transformReceiver(context, intent);
        }
    }

    private class RegisterBroadcastEntity {
        public final BroadcastReceiver receiver;
        public final IntentFilter filter;

        public RegisterBroadcastEntity(BroadcastReceiver receiver, IntentFilter filter) {
            this.receiver = receiver;
            this.filter = filter;
        }
    }
}
