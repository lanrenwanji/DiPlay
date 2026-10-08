package com.shilapi.xcertplay

import android.content.Context
import android.provider.Settings

/**
 * Changan UNI-V "three-finger fling" (三指飞屏) support.
 */
internal object UniVFling {
    const val ACTION_SWITCH_SCREEN = "com.rmt.action.SWITCH_SCREEN"
    const val INTERNAL_ACTION = "com.shihab.diplay.action.UNIV_FLING"
    const val SETTING_SPLIT_SCREEN = "rmt_split_screen"
    const val EXTRA_MODE = "mode"
    const val DEFAULT_MODE = 2
    const val MODE_FULL_PANEL = 3

    val ACTIONS: List<String> = listOf(
        ACTION_SWITCH_SCREEN,
        "com.incall.action.SCREEN_MODE_CHANGE",
        "com.incall.action.SCREEN_STATUS",
        "com.incall.action.REQUEST_MAP_STATUS",
        "com.incall.action.CLUSTER_TBT_SHOW",
        "com.incall.action.INSTRUMENTATION_THEME_CHANGE",
        "com.incall.action.IACCMODE_CHANGE",
        "com.incall.action.carmodechange",
        "com.neusoft.action.carmodechange",
    )

    val TRIGGER_ACTIONS: List<String> = listOf(
        "com.wt.phonelink.action.LINK_CONTROL",
        "com.wt.phonelink.keycode.ACTION_KEYCODE_CUSTOM",
        "com.incall.apps.hicar.ACTION_START_MAINACTIVITY",
    )

    fun isFlingAction(action: String?): Boolean = action != null && ACTIONS.any { it == action }
    fun isTriggerAction(action: String?): Boolean = action != null && TRIGGER_ACTIONS.any { it == action }

    fun modeFromSettings(context: Context): Int =
        runCatching {
            Settings.System.getInt(context.contentResolver, SETTING_SPLIT_SCREEN, DEFAULT_MODE)
        }.getOrDefault(DEFAULT_MODE)

    fun shouldShowCluster(mode: Int): Boolean = mode == MODE_FULL_PANEL
}
