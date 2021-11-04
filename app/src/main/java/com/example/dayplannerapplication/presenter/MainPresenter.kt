package com.example.dayplannerapplication.presenter

import android.content.Context
import android.widget.Toast
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

    fun adapterClick(applicationContext: Context, task: Task) {
        /*
       val intent = Intent(this, FlowerDetailActivity()::class.java)
       intent.putExtra(FLOWER_ID, flower.id)
       startActivity(intent)
        */
        Toast.makeText(applicationContext, "Click on Presenter", Toast.LENGTH_SHORT).show()
    }

    fun calendarClick(year: Int, month: Int, day: Int) {
        loadListTaskForDateTime()
    }
}
