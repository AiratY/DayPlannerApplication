package com.example.dayplannerapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dayplannerapplication.data.Task
import com.example.dayplannerapplication.taskList.TasksAdapter
import com.example.dayplannerapplication.taskList.TasksListViewModel
import com.example.dayplannerapplication.taskList.TasksListViewModelFactory

class MainActivity : AppCompatActivity() {

    // private val newFlowerActivityRequestCode = 1
    private val tasksListViewModel by viewModels<TasksListViewModel> {
        TasksListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tasksAdapter = TasksAdapter { task -> adapterOnClick(task) }

        val recyclerView: RecyclerView = findViewById(R.id.tasksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = tasksAdapter

        tasksListViewModel.tasksLiveData.observe(this, {
            it?.let {
                tasksAdapter.submitList(it as MutableList<Task>)
            }
        })
    }
    private fun adapterOnClick(task: Task) {
        /*
        val intent = Intent(this, FlowerDetailActivity()::class.java)
        intent.putExtra(FLOWER_ID, flower.id)
        startActivity(intent)
         */
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
    }
}
