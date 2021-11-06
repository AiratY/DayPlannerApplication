package com.example.dayplannerapplication.presenter

import com.example.dayplannerapplication.data.DataSource
import com.example.dayplannerapplication.data.Task
import com.example.dayplannerapplication.presenter.usecase.SetTimeDate
import com.example.dayplannerapplication.view.MainContractView
import java.util.Date

class MainPresenter {

    private var view: MainContractView? = null
    private var dataSource: DataSource? = null
    private lateinit var dateLoud: Date
    private var yearLoad = 0
    private var monthLoad = 0
    private var dayLoad = 0

    private fun loadListTaskToday() {
        val dateStart = Date()
        val dateEnd = Date()
        yearLoad = dateStart.year + 1900
        monthLoad = dateStart.month
        dayLoad = dateStart.date
        SetTimeDate().execute(dateStart,0,0)
        SetTimeDate().execute(dateEnd,23,59)
        loadTaskList(dateStart.time, dateEnd.time)
    }

    private fun loadTaskList(dateStart: Long, dateEnd: Long) {
        val taskList = dataSource?.getTaskListForDateTime(dateStart, dateEnd)
        if (taskList == null || taskList.count() == 0) {
            view?.showMessageNoTask()
        } else {
            view?.showTasks(taskList)
        }
    }

    private fun loadListTaskForDateTime(year: Int, month: Int, day: Int) {
        val dateStart = Date(year - 1900, month, day, 0, 0)
        val dateEnd = Date(year - 1900, month, day, 23, 59)
        val taskList = dataSource?.getTaskListForDateTime(dateStart.time, dateEnd.time)
        if (taskList == null || taskList.count() == 0) {
            view?.showMessageNoTask()
        } else {
            view?.showTasks(taskList)
        }
    }

    fun attachView(contractView: MainContractView) {
        view = contractView
    }

    private fun detachView() {
        view = null
    }

    fun viewIsReady() {
        dataSource = view?.getContext()?.let { DataSource.getDataSource(it) }
        loadListTaskToday()
    }

    fun adapterClick(task: Task) {
        view?.moveOnDetailTaskActivity(task.id)
    }

    fun calendarClick(year: Int, month: Int, day: Int) {
        if(year != yearLoad || month != monthLoad || day != dayLoad){
            yearLoad = year
            monthLoad = month
            dayLoad = day
            loadListTaskForDateTime(year, month, day)
        }
    }

    fun fabOnClick() {
        view?.moveOnAddTaskActivity()
    }

    fun viewDestroy() {
        detachView()
        //dataSource?.closeRealmConnection()
    }
}
