package com.example.dayplannerapplication.presenter

import com.example.dayplannerapplication.FORMAT_DATE
import com.example.dayplannerapplication.FORMAT_TIME
import com.example.dayplannerapplication.data.DataSource
import com.example.dayplannerapplication.data.Task
import com.example.dayplannerapplication.presenter.usecase.ConvertDateToFormat
import com.example.dayplannerapplication.view.DetailTaskContractView
import com.example.dayplannerapplication.view.models.descTask
import java.util.Date

class DetailTaskPresenter {
    private var view: DetailTaskContractView? = null
    private var dataSource: DataSource? = null
    private var taskId: Int = -1

    fun attachView(contractView: DetailTaskContractView) {
        view = contractView
    }

    fun viewDestroy() {
        view = null
        dataSource?.closeRealmConnection()
    }

    fun viewIsReady() {
        dataSource = view?.getContext()?.let { DataSource.getDataSource(it) }
        view?.getId()
    }

    fun change() {
        view?.moveToEditActivity(taskId)
    }

    fun delete() {
        dataSource?.removeTask(taskId)
        view?.moveToMain()
    }

    fun getId(idTask: Int) {
        taskId = idTask
        val task: Task? = dataSource?.getTaskForId(idTask)
        if (task != null) {
            val timeStart: String = ConvertDateToFormat().execute(Date(task.dateStart), FORMAT_TIME)
            val timeEnd: String = ConvertDateToFormat().execute(Date(task.dateEnd), FORMAT_TIME)
            val date = ConvertDateToFormat().execute(Date(task.dateEnd), FORMAT_DATE)
            val descTask = descTask(date = date, time = "$timeStart-$timeEnd", name = task.name, desc = task.description)
            view?.loudeTask(descTask)
        } else {
            view?.moveToMain()
        }
    }
}
