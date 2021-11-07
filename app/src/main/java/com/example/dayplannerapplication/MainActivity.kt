package com.example.dayplannerapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dayplannerapplication.data.Task
import com.example.dayplannerapplication.presenter.MainPresenter
import com.example.dayplannerapplication.taskList.TasksAdapter
import com.example.dayplannerapplication.view.AddTaskActivity
import com.example.dayplannerapplication.view.DetailTaskActivity
import com.example.dayplannerapplication.view.MainContractView

class MainActivity : AppCompatActivity(), MainContractView {
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var mainPresenter: MainPresenter
    private lateinit var message: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init() {
        mainPresenter = MainPresenter()
        val calendarView: CalendarView = findViewById(R.id.calendarView)
        tasksAdapter = TasksAdapter { task -> mainPresenter.adapterClick(task) }
        val recyclerView: RecyclerView = findViewById(R.id.tasksRecyclerView)
        message = findViewById(R.id.messageTextView)
        mainPresenter.attachView(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = tasksAdapter

        mainPresenter.viewIsReady()

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            mainPresenter.fabOnClick()
        }

        calendarView.setOnDateChangeListener { _, year, month, day ->
            mainPresenter.calendarClick(year, month, day)
        }
    }

    override fun showTasks(taskList: List<Task>) {
        message.visibility = View.INVISIBLE
        tasksAdapter.submitList(taskList)
    }

    override fun showMessageNoTask() {
        message.visibility = View.VISIBLE
        tasksAdapter.submitList(null)
    }
    override fun moveOnDetailTaskActivity(id: Int) {
        val intent = Intent(this, DetailTaskActivity()::class.java)
        intent.putExtra(TASK_ID, id)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.viewDestroy()
    }

    override fun moveOnAddTaskActivity() {
        startActivity(Intent(this, AddTaskActivity::class.java))
    }

    override fun getContext(): Context = applicationContext
}
