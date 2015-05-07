package com.example.muhammadfaizankhan.geofence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Muhammad Faizan Khan on 07/05/2015.
 */

public class MyReciever extends BroadcastReceiver
{

    private static final String TAG = "Muzammil";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "in onReceive");
        context.startService(new Intent(context.getApplicationContext(), MyService.class));

    }

}
