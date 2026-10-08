package com.shilapi.xcertplay

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/** Car projection hard-key entry: bring DiPlay to foreground. */
class CarProjectionTriggerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (!UniVFling.isTriggerAction(intent.action)) return
        Log.i(TAG, "projection trigger action=${intent.action}")
        val launch = Intent().apply {
            setClassName(context.packageName, "com.shilapi.xcertplay.DiPlayActivity")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        runCatching { context.startActivity(launch) }
            .onFailure { Log.w(TAG, "could not launch DiPlay", it) }
    }

    companion object {
        private const val TAG = "DiPlay-Fling"
    }
}
