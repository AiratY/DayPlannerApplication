package com.example.dayplannerapplication.presenter

import com.example.dayplannerapplication.MainActivity
import com.example.dayplannerapplication.data.DataSource
import com.example.dayplannerapplication.data.Task

class MainPresenter {

    private var view: MainActivity? = null

    private fun loadListTask() {
        val dataSource = DataSource.getDataSource()
        val taskList = dataSource.getTaskList()
        if (taskList == null) {
            view?.showMessageNoTask()
        } else {
            view?.showTasks(taskList)
        }
    }

    private fun loadListTaskForDateTime() {
        val dataSource = DataSource.getDataSource()
        val taskList = dataSource.getTaskListForDateTime()
        if (taskList == null) {
            view?.showMessageNoTask()
        } else {
            view?.showTasks(taskList)
        }
    }

    fun attachView(mainActivity: MainActivity) {
        view = mainActivity
    }

    fun detachView() {
        view = null
    }

    fun viewIsReady() {
        loadListTask()
    }

    fun adapterClick(task: Task) {
        view?.moveOnDetailTaskActivity(task.id)
    }

    fun calendarClick(year: Int, month: Int, day: Int) {
        loadListTaskForDateTime()
    }

    fun fabOnClick() {
        view?.moveOnAddTaskActivity()
    }
}
