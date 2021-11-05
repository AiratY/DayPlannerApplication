package com.example.dayplannerapplication.presenter.usecase

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class ConvertDateToFormat {
    fun execute(time: Date, format: String): String {
        val dateFormat: DateFormat = SimpleDateFormat(format)
        return dateFormat.format(time)
    }
}
