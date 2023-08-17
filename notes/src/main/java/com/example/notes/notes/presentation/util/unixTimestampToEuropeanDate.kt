package com.example.notes.notes.presentation.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun unixTimestampToEuropeanDate(unixTimestamp: Long): String {
    val date = Date(unixTimestamp * 1000)
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
    return dateFormat.format(date)
}

fun getDayAndMonthFromUnixTimestamp(unixTimestampMillis: Long): Pair<Int, Int> {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = unixTimestampMillis

    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH) + 1

    return Pair(dayOfMonth, month)
}