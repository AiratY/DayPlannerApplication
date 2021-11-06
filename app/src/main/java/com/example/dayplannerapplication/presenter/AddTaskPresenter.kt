package com.example.dayplannerapplication.presenter

import com.example.dayplannerapplication.data.DataSource
import com.example.dayplannerapplication.data.Task
import com.example.dayplannerapplication.view.AddTaskContractView
import com.example.dayplannerapplication.view.models.dataTask
import java.util.Date
import kotlin.random.Random

class AddTaskPresenter {
    private var view: AddTaskContractView? = null
    private var dataSource: DataSource? = null
    private var isChange: Boolean = false
    private var taskId: Int = -1

    fun attachView(contractView: AddTaskContractView) {
        view = contractView
        dataSource = view?.getContext()?.let { DataSource.getDataSource(it) }
        val taskId = view?.checkId()
        if (taskId != null && taskId != -1) {
            getId(taskId)
        }
    }

    private fun getId(idTask: Int) {
        val task: Task? = dataSource?.getTaskForId(idTask)
        if (task != null) {
            val year = Date(task.dateStart).year + 1900
            val month = Date(task.dateStart).month
            val day = Date(task.dateStart).date
            val hours = Date(task.dateStart).hours
            val min = Date(task.dateStart).minutes
            taskId = idTask
            isChange = true
            view?.loudeTask(task.name, task.description, year, month, day, hours, min)
        }
    }

    fun viewDestroy() {
        view = null
        //dataSource?.closeRealmConnection()
    }

    fun save() {
        view?.getData()
    }

    fun setData(dataTask: dataTask) {
        if (dataTask.name.isEmpty()) {
            view?.showMessageNullName()
        } else {
            val id = if (isChange && taskId != -1) {
                taskId
            } else {
                Random.nextInt()
            }
            val task = Task(
                id = id,
                dateStart = dataTask.dateStart.time,
                dateEnd = dataTask.dateEnd.time,
                name = dataTask.name,
                description = dataTask.description
            )
            if (isChange) {
                dataSource?.changeTask(task)
            } else {
                dataSource?.addTask(task)
            }
            view?.moveToMain()
        }
    }
}
