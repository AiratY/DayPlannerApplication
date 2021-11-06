package com.example.dayplannerapplication.presenter

import com.example.dayplannerapplication.data.DataSource
import com.example.dayplannerapplication.data.Task
import com.example.dayplannerapplication.presenter.usecase.SetTimeDate
import com.example.dayplannerapplication.view.MainContractView
import java.util.Date

class MainPresenter {

    private var view: MainContractView? = null
    private var dataSource: DataSource? = null
    private var dataStartLoude: Long = 0
    private var dataEndLoude: Long = 0

    private fun loadListTaskToday() {
        val dateStart = Date()
        val dateEnd = Date()
        SetTimeDate().execute(dateStart,0,0)
        SetTimeDate().execute(dateEnd,23,59)
        loadTaskList(dateStart.time, dateEnd.time)
    }

    private fun loadTaskList(dateStart: Long, dateEnd: Long) {
        if (dataStartLoude == dateStart && dataEndLoude == dateEnd) {
            return
        } else {
            dataStartLoude = dateStart
            dataEndLoude = dateEnd
            val taskList = dataSource?.getTaskListForDateTime(dateStart, dateEnd)
            if (taskList == null || taskList.count() == 0) {
                view?.showMessageNoTask()
            } else {
                view?.showTasks(taskList)
            }
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
        loadListTaskForDateTime(year, month, day)
    }

    fun fabOnClick() {
        view?.moveOnAddTaskActivity()
    }

    fun viewDestroy() {
        detachView()
        //dataSource?.closeRealmConnection()
    }
}
