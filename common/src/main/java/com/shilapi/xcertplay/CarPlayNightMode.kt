package com.shilapi.xcertplay

import java.util.Calendar

enum class CarPlayNightMode(val key: String) {
    SYSTEM("system"),
    AMBIENT("ambient"),
    DAY("day"),
    NIGHT("night"),
    SCHEDULE("schedule");

    companion object {
        fun fromKey(key: String?): CarPlayNightMode = entries.firstOrNull { it.key == key } ?: NIGHT
    }
}

data class CarPlayNightSchedule(val startMinute: Int = 18 * 60, val endMinute: Int = 6 * 60) {
    init {
        require(startMinute in 0 until 24 * 60 && endMinute in 0 until 24 * 60)
    }

    fun isNight(minuteOfDay: Int): Boolean = when {
        startMinute == endMinute -> false
        startMinute < endMinute -> minuteOfDay in startMinute until endMinute
        else -> minuteOfDay >= startMinute || minuteOfDay < endMinute
    }
}
