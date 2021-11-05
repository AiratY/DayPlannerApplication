package com.example.dayplannerapplication.taskList

import androidx.lifecycle.ViewModel
import com.example.dayplannerapplication.data.DataSource

class TasksListViewModel(val dataSource: DataSource) : ViewModel() {
    //val tasksLiveData = dataSource.getTaskList()

/*
    fun insertFlower(taskName: String?, flowerDescription: String?) {
        if (flowerName == null || flowerDescription == null) {
            return
        }

        val image = dataSource.getRandomFlowerImageAsset()
        val newFlower = Flower(
                Random.nextLong(),
                flowerName,
                image,
                flowerDescription
        )

        dataSource.addFlower(newFlower)
    }

 */
}
