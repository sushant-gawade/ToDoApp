package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onDeleteClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // ViewHolder class to manage the individual task item views
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskName: TextView = itemView.findViewById(R.id.taskName)
        val taskCheckBox: CheckBox = itemView.findViewById(R.id.taskCheckBox)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    // Inflate the task item layout and create the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    // Bind the task data to the ViewHolder for display
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskName.text = task.taskName
        holder.taskCheckBox.isChecked = task.isCompleted

        // Handle checkbox change to mark task as completed
        holder.taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
        }

        // Handle delete button click to remove the task
        holder.deleteButton.setOnClickListener {
            onDeleteClick(task)
        }
    }

    // Return the total number of tasks
    override fun getItemCount(): Int {
        return tasks.size
    }
}
