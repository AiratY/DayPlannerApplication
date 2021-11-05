package com.example.dayplannerapplication.data

import android.content.Context
import com.example.dayplannerapplication.KEY_DATE_TASK
import com.example.dayplannerapplication.KEY_ID_TASK
import com.example.dayplannerapplication.data.usecase.RealmClose
import com.example.dayplannerapplication.data.usecase.RealmInit
import io.realm.Realm
import io.realm.kotlin.where
import java.sql.Timestamp

class DataSource(val context: Context) {
    //private val initialTaskList = tasksList()
    //private val tasksLiveData = MutableLiveData(initialTaskList)
    private var realm: Realm = RealmInit().execute(context)

    fun addTask(task: Task) {
        realm.executeTransaction { transactionRealm ->
            transactionRealm.insert(task)
        }
    }

    fun changeTask(task: Task) {
        realm.executeTransaction { transactionRealm ->
            val changeTask : Task? = transactionRealm.where<Task>().equalTo(KEY_ID_TASK, task.id).findFirst()
            changeTask?.let{
                it.name = task.name
                it.description = task.description
                it.dateStart = task.dateStart
                it.dateEnd = task.dateEnd
            }
        }
    }

    fun removeTask(taskId: Int) {
        realm.executeTransaction {
            getTaskForId(taskId)?.deleteFromRealm()
        }
    }

    fun getTaskForId(id: Int): Task? {
        return realm.where<Task>().equalTo(KEY_ID_TASK, id).findFirst()
    }

    fun getTaskList(): List<Task> {
        return realm.where<Task>().findAll()
    }
    fun getTaskListForDateTime(dateStart: Long, dateEnd: Long): List<Task> {
        val someRealm = Realm.getDefaultInstance()
        //val taskList = realm.where<Task>().between(KEY_DATE_TASK, dateStart, dateEnd).findAllAsync()
        val taskList = someRealm.where<Task>().findAll()
        someRealm.close()
        return taskList
    }

    fun closeRealmConnection(){
        RealmClose().execute(realm)
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(context: Context): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(context)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}
