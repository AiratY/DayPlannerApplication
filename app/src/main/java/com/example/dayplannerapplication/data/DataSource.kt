package com.example.dayplannerapplication.data

import android.content.Context
import com.example.dayplannerapplication.KEY_DATE_TASK
import com.example.dayplannerapplication.KEY_ID_TASK
import com.example.dayplannerapplication.data.usecase.RealmClose
import com.example.dayplannerapplication.data.usecase.RealmInit
import io.realm.Realm
import io.realm.kotlin.where

class DataSource(val context: Context) {

    private var realm: Realm

    init {
        RealmInit().execute(context)
        realm = Realm.getDefaultInstance()
    }
    fun addTask(task: Task) {
        realm.executeTransaction { transactionRealm ->
            transactionRealm.insert(task)
        }
    }

    fun changeTask(task: Task) {
        realm.executeTransaction { transactionRealm ->
            val changeTask: Task? = transactionRealm.where<Task>().equalTo(KEY_ID_TASK, task.id).findFirst()
            changeTask?.let {
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

    fun getTaskListForDateTime(dateStart: Long, dateEnd: Long): List<Task> {
        return realm.where<Task>().between(KEY_DATE_TASK, dateStart, dateEnd).findAll().sort(KEY_DATE_TASK).toList()
    }

    fun closeRealmConnection() {
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
