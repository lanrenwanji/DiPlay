package com.shilapi.xcertplay

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/** Three-finger fling entry point. */
class UniVFlingReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (!UniVFling.isFlingAction(intent.action)) return
        val mode = UniVFling.modeFromSettings(context)
        Log.i(TAG, "fling action=${intent.action} mode=$mode")
        val forward = Intent(UniVFling.INTERNAL_ACTION).apply {
            setPackage(context.packageName)
            putExtra(UniVFling.EXTRA_MODE, mode)
        }
        context.sendBroadcast(forward)
    }

    companion object {
        private const val TAG = "DiPlay-Fling"
    }
}
