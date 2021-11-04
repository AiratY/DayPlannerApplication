package com.example.dayplannerapplication.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dayplannerapplication.MainActivity
import com.example.dayplannerapplication.R
import com.example.dayplannerapplication.presenter.AddTaskPresenter
import com.example.dayplannerapplication.presenter.DetailTaskPresenter
import com.example.dayplannerapplication.view.models.descTask

class AddTaskActivity : AppCompatActivity(), AddTaskContractView {

    private lateinit var nameEditView: EditText
    private lateinit var descEditView: EditText
    private lateinit var presenter: AddTaskPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        init()
    }
    private fun init() {
        nameEditView = findViewById(R.id.nameTaskEditText)
        descEditView = findViewById(R.id.descTaskEditText)
        presenter = AddTaskPresenter()
        findViewById<Button>(R.id.saveTaskButton).setOnClickListener {
            presenter.attachView(this)
            presenter.save()
        }
    }

    override fun getData() {
        val descTask = descTask("date", "time", name = nameEditView.toString(), desc = descEditView.toString())
        presenter.setData(descTask)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showMessageNullName() {
        nameEditView.hint = "Поле Название обязательно для заполнения"
    }

    override fun moveToMain() {
        startActivity(Intent(applicationContext, MainActivity()::class.java))
    }
}
