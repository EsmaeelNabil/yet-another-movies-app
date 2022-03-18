package com.esmaeel.challenge.data.remote.models.adapters

import android.annotation.SuppressLint
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class DateJsonAdapter {
    private val dateFormat: DateFormat

    init {
        dateFormat = SimpleDateFormat("yyyy-MM-dd")
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    }

    @ToJson
    @Synchronized
    internal fun dateToJson(date: Date): String {
        return dateFormat.format(date)
    }

    @FromJson
    @Synchronized
    @Throws(ParseException::class)
    internal fun dateFromJson(dateString: String): Date {
        return dateFormat.parse(dateString) ?: Date()
    }
}