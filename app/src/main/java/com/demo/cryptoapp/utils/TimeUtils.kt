package com.demo.cryptoapp.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun convertTimestampToTime(timestamp: Int?): String{
    if (timestamp == null) return ""
    val stamp = Timestamp(timestamp.toLong() * 1000)
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}