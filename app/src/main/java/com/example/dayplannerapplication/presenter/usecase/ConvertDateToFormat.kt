package com.example.dayplannerapplication.presenter.usecase

import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ConvertDateToFormat {
    fun execute(time: Timestamp, format: String): String {
        val dateFormat: DateFormat = SimpleDateFormat(format)
        return dateFormat.format(Date(time.time))
    }
}
