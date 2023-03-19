package com.example.notesdemo.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object TimeUtils {

    fun getCurrentDateTime(): String {
        val time = Calendar.getInstance().time
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss dd-MM-yyyy", Locale.getDefault())
        return simpleDateFormat.format(time)
    }
}