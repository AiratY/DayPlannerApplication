package com.example.dayplannerapplication.view

import android.content.Context

interface AddTaskContractView {
    fun getData()
    fun showMessageNullName()
    fun moveToMain()
    fun getContext(): Context
    fun checkId(): Int
    fun loudeTask(
        name: String,
        description: String,
        year: Int,
        month: Int,
        day: Int,
        hours: Int,
        min: Int
    )
}
