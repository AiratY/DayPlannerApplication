package com.example.dayplannerapplication.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
    private val initialTaskList = tasksList(resources)
    private val tasksLiveData = MutableLiveData(initialTaskList)

    fun addTask(task: Task) {
        val currentList = tasksLiveData.value
        if (currentList == null) {
            tasksLiveData.postValue(listOf(task))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, task)
            tasksLiveData.postValue(updatedList)
        }
    }

    fun removeTask(task: Task) {
        val currentList = tasksLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(task)
            tasksLiveData.postValue(updatedList)
        }
    }

    fun getTaskForId(id: Int): Task? {
        tasksLiveData.value?.let { task ->
            return task.firstOrNull { it.id == id }
        }
        return null
    }

    fun getTaskList(): LiveData<List<Task>> {
        return tasksLiveData
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}
