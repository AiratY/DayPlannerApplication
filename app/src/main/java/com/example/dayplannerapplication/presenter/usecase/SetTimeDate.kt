package com.example.dayplannerapplication.presenter.usecase

import java.util.Date

class SetTimeDate {
    fun execute(date: Date, hours: Int, minutes: Int){
        date.hours = hours
        date.minutes = minutes
    }
}