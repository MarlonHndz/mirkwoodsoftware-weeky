package com.mirkwoodsoftware.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

object DateUtils {
    const val UTC = "UTC"
    const val dd_MM_yyyy = "dd/MM/yyyy"

    fun convertUtcToLocal(longValue: Long, dateFormatPattern: String): String {
        val dateFormat = SimpleDateFormat(dateFormatPattern)
        dateFormat.timeZone = TimeZone.getTimeZone(UTC)
        val dateUtc = Date(longValue)
        return dateFormat.format(dateUtc)
    }
}
