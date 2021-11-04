package com.example.dayplannerapplication.view

import com.example.dayplannerapplication.view.models.descTask

interface DetailTaskContractView {
    fun loudeTask(descTask: descTask)
    fun getId()
    fun moveToMain()
    fun moveToEditActivity(id: Int)
}
