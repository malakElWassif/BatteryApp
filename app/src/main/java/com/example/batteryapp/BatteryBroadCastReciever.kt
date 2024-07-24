package com.example.batteryapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager

class BatteryBroadCastReciever(val onBatteryLowChanged: (Boolean) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action == Intent.ACTION_BATTERY_LOW) {
            onBatteryLowChanged(true)


        } else if(intent!!.action == Intent.ACTION_BATTERY_OKAY)
            onBatteryLowChanged(false)

    }
}