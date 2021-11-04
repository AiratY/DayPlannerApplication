package com.example.dayplannerapplication.presenter

import com.example.dayplannerapplication.data.DataSource
import com.example.dayplannerapplication.data.Task
import com.example.dayplannerapplication.view.AddTaskContractView
import com.example.dayplannerapplication.view.models.descTask
import java.sql.Timestamp
import java.util.*

class AddTaskPresenter {
    private var view: AddTaskContractView? = null
    private val dataSource = DataSource.getDataSource()

    fun attachView(contractView: AddTaskContractView) {
        view = contractView
    }

    fun detachView() {
        view = null
    }

    fun save(){
        view?.getData()
    }

    fun setData(descTask: descTask) {
        if(descTask.name.isEmpty()){
            view?.showMessageNullName()
        } else {
            val task = Task(
                id = 0,
                dateStart = Timestamp(Date().time),
                dateEnd = Timestamp(Date().time),
                name = descTask.name,
                description = descTask.desc
            )
            dataSource.addTask(task)
            view?.moveToMain()
        }
    }
}