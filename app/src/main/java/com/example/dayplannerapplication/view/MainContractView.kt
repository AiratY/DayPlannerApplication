package com.example.dayplannerapplication.view

import android.content.Context
import com.example.dayplannerapplication.data.Task

interface MainContractView {
    fun showMessageNoTask()
    fun showTasks(taskList: List<Task>)
    fun moveOnDetailTaskActivity(id: Int)
    fun moveOnAddTaskActivity()
    fun getContext(): Context
}
