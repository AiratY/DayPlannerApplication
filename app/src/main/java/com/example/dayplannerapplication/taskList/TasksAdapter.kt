package com.example.dayplannerapplication.taskList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dayplannerapplication.R
import com.example.dayplannerapplication.data.Task

class TasksAdapter(private val onClick: (Task) -> Unit) :
    ListAdapter<Task, TasksAdapter.TaskViewHolder>(TaskDiffCallback) {

    class TaskViewHolder(itemView: View, val onClick: (Task) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val taskNameTextView: TextView = itemView.findViewById(R.id.taskNameTextView)
        private val taskTimeTextView: TextView = itemView.findViewById(R.id.taskTimeTextView)
        private var currentTask: Task? = null

        init {
            itemView.setOnClickListener {
                currentTask?.let {
                    onClick(it)
                }
            }
        }

        fun bind(task: Task) {
            currentTask = task

            taskNameTextView.text = task.name
            taskTimeTextView.text = task.dateStart.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }
}

object TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }
}
