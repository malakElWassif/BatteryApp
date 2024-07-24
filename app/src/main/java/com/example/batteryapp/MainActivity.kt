package com.example.batteryapp

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.batteryapp.ui.theme.BatteryAppTheme
import android.content.Context
import androidx.core.content.ContextCompat.registerReceiver

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isBatteryLow by remember { mutableStateOf(false) }
            val batteryLowReceiver = BatteryBroadCastReciever { isLow ->
                isBatteryLow = isLow
            }
            val filter = IntentFilter().apply {
                addAction(Intent.ACTION_BATTERY_LOW)
                addAction(Intent.ACTION_BATTERY_OKAY)
            }
            registerReceiver(batteryLowReceiver, filter)




            BatteryAppTheme {
                Battery(isBatteryLow)
            }
        }
    }
}


@Composable
fun Battery(batteryLow:Boolean,modifier: Modifier = Modifier) {



    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        if(batteryLow)
        Image(painter = painterResource(id = R.drawable.battery_low), contentDescription = "")
        else
        Image(painter = painterResource(id = R.drawable.battery_full), contentDescription = "")
    }

}



@Preview(showBackground = true)
@Composable
fun BatteryPreview() {
    Battery(false)
}
