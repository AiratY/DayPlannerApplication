package com.example.dayplannerapplication.presenter

import android.content.Context
import com.example.dayplannerapplication.data.DataSource
import com.example.dayplannerapplication.data.Task
import com.example.dayplannerapplication.view.MainContractView
import java.util.*

class MainPresenter {

    private var view: MainContractView? = null
    private var dataSource: DataSource? = null
    private lateinit var  context: Context

    private fun loadListTask() {
        //val dataSource = DataSource.getDataSource(context)
        val dateStart = Date(2021-1900,11,5, 0,0)
        val dateEnd = Date(2021-1900,11,5, 23,59)
        val taskList = dataSource?.getTaskListForDateTime(dateStart.time, dateEnd.time)
        //val taskList = dataSource?.getTaskList()
        if (taskList == null) {
            view?.showMessageNoTask()
        } else {
            view?.showTasks(taskList)
        }
    }

    private fun loadListTaskForDateTime(year: Int, month: Int, day: Int) {
        //val dataSource = DataSource.getDataSource(context)
        val dateStart = Date(year-1900,month,day, 0,0)
        val dateEnd = Date(year-1900,month,day, 23,59)
        //val taskList = dataSource?.getTaskListForDateTime(dateStart.time, dateEnd.time)
        val taskList = dataSource?.getTaskList()
        if (taskList == null) {
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
        loadListTask()
    }

    fun adapterClick(task: Task) {
        view?.moveOnDetailTaskActivity(task.id)
    }

    fun calendarClick(year: Int, month: Int, day: Int) {
       loadListTaskForDateTime( year, month, day)
    }

    fun fabOnClick() {
        view?.moveOnAddTaskActivity()
    }

    fun viewDestroy() {
        detachView()
       // dataSource?.closeRealmConnection()
    }
}
