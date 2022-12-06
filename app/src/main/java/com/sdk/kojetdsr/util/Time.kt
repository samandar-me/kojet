package com.sdk.kojetdsr.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

object Time {
    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("EEE,MMM dd", Locale.getDefault())
        return dateFormat.format(Date())
    }
}