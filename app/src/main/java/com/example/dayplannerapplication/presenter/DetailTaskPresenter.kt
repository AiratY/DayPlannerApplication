package com.example.dayplannerapplication.presenter

import com.example.dayplannerapplication.FORMAT_DATE
import com.example.dayplannerapplication.FORMAT_TIME
import com.example.dayplannerapplication.data.DataSource
import com.example.dayplannerapplication.data.Task
import com.example.dayplannerapplication.presenter.usecase.ConvertDateToFormat
import com.example.dayplannerapplication.view.DetailTaskContractView
import com.example.dayplannerapplication.view.models.descTask

class DetailTaskPresenter {
    private var view: DetailTaskContractView? = null
    private val dataSource = DataSource.getDataSource()
    private var taskId: Int = -1

    fun attachView(contractView: DetailTaskContractView) {
        view = contractView
    }

    fun detachView() {
        view = null
    }

    fun viewIsReady() {
        view?.getId()
    }

    fun change() {
        view?.moveToEditActivity(taskId)
    }

    fun delete() {
        val task: Task? = dataSource.getTaskForId(taskId)
        task?.let { dataSource.removeTask(it) }
        view?.moveToMain()
    }

    fun getId(idTask: Int) {
        taskId = idTask
        val task: Task? = dataSource.getTaskForId(idTask)
        if (task != null) {
            val timeStart: String = ConvertDateToFormat().execute(task.dateStart, FORMAT_TIME)
            val timeEnd: String = ConvertDateToFormat().execute(task.dateEnd, FORMAT_TIME)
            val date: String = ConvertDateToFormat().execute(task.dateEnd, FORMAT_DATE)
            val descTask = descTask(date = date, time = "$timeStart-$timeEnd", name = task.name, desc = task.description)
            view?.loudeTask(descTask)
        } else {
            view?.moveToMain()
        }
    }
}
