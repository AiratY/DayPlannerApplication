package com.example.dayplannerapplication.data

import androidx.lifecycle.MutableLiveData

class DataSource() {
    private val initialTaskList = tasksList()
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

    fun getTaskList(): List<Task> {
        return initialTaskList
    }
    fun getTaskListForDateTime(): List<Task> {
        return listOf(initialTaskList[0], initialTaskList[1])
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}
