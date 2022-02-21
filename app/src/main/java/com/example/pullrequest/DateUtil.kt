package com.example.pullrequest

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created in order to format date to human readable format
 */
object DateUtil {
    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    private val outputFormat = SimpleDateFormat("dd MMM yyyy");

    fun getFormattedDate(timeStamp: String?): String {
        var date: Date? = null
        try {
            date = inputFormat.parse(timeStamp)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        return outputFormat.format(date)
    }
}