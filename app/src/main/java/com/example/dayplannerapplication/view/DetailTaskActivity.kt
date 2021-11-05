package com.example.dayplannerapplication.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dayplannerapplication.MainActivity
import com.example.dayplannerapplication.R
import com.example.dayplannerapplication.TASK_ID
import com.example.dayplannerapplication.presenter.DetailTaskPresenter
import com.example.dayplannerapplication.view.models.descTask

class DetailTaskActivity : AppCompatActivity(), DetailTaskContractView {

    private lateinit var dateTextView: TextView
    private lateinit var timeTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var descTextView: TextView
    private lateinit var presenter: DetailTaskPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_task)
        init()
    }

    private fun init() {
        presenter = DetailTaskPresenter()
        dateTextView = findViewById(R.id.dateTextView)
        timeTextView = findViewById(R.id.timeTextView)
        nameTextView = findViewById(R.id.nameTaskTextView)
        descTextView = findViewById(R.id.descTaskTextView)
        presenter.attachView(this)
        presenter.viewIsReady()

        findViewById<Button>(R.id.changeTaskButton).setOnClickListener() {
            presenter.change()
        }
        findViewById<Button>(R.id.deleteTaskButton).setOnClickListener() {
            presenter.delete()
        }
    }

    override fun loudeTask(descTask: descTask) {
        dateTextView.text = descTask.date
        timeTextView.text = descTask.time
        nameTextView.text = descTask.name
        descTextView.text = descTask.desc
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.viewDestroy()
    }
    override fun getId() {
        val bundle: Bundle? = intent.extras
        var idTask: Int
        if (bundle != null) {
            idTask = bundle.getInt(TASK_ID)
            presenter.getId(idTask)
        }
    }

    override fun moveToMain() {
        startActivity(Intent(applicationContext, MainActivity()::class.java))
    }
    override fun moveToEditActivity(id: Int) {
        val intent = Intent(this, AddTaskActivity()::class.java)
        intent.putExtra(TASK_ID, id)
        startActivity(intent)
    }

    override fun getContext(): Context = applicationContext
}
