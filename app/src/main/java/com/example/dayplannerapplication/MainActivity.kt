package com.example.dayplannerapplication

import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dayplannerapplication.data.Task
import com.example.dayplannerapplication.presenter.MainPresenter
import com.example.dayplannerapplication.taskList.TasksAdapter

class MainActivity : AppCompatActivity() {
    /*
    private val tasksListViewModel by viewModels<TasksListViewModel> {
        TasksListViewModelFactory(this)
    }*/
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init() {
        mainPresenter = MainPresenter()
        val calendarView: CalendarView = findViewById(R.id.calendarView)
        tasksAdapter = TasksAdapter { task -> mainPresenter.adapterClick(applicationContext, task) }
        val recyclerView: RecyclerView = findViewById(R.id.tasksRecyclerView)

        mainPresenter.attachView(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = tasksAdapter

        mainPresenter.viewIsReady()

        /*
        tasksListViewModel.tasksLiveData.observe(this, {
            it?.let {
                tasksAdapter.submitList(it as MutableList<Task>)
            }
        })*/

        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            val monthNormal = month + 1 // Отсчёт месяцев с 0
            mainPresenter.calendarClick(year, monthNormal, day)
        }
    }

    fun showTasks(taskList: List<Task>) {
        tasksAdapter.submitList(taskList)
    }

    fun showMessageNoTask() {
        Toast.makeText(applicationContext, "No tasks", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }
}
