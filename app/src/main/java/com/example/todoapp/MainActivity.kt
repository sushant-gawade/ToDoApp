package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var tasks: MutableList<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the task list
        tasks = mutableListOf()

        // Find the RecyclerView and set up the adapter
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        taskAdapter = TaskAdapter(tasks) { taskToRemove ->
            removeTask(taskToRemove)
        }
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Find the EditText and Button
        val addTaskButton: Button = findViewById(R.id.buttonAddTask)
        val taskEditText: EditText = findViewById(R.id.editTextTask)

        // Set the button's onClick listener to add a new task
        addTaskButton.setOnClickListener {
            val taskName = taskEditText.text.toString()
            if (taskName.isNotEmpty()) {
                addTask(taskName)
                taskEditText.text.clear() // Clear the input field after adding the task
            }
        }
    }

    // Function to add a task to the list
    private fun addTask(taskName: String) {
        val task = Task(taskName)
        tasks.add(task)
        taskAdapter.notifyItemInserted(tasks.size - 1)
    }

    // Function to remove a task from the list
    private fun removeTask(task: Task) {
        val position = tasks.indexOf(task)
        if (position != -1) {
            tasks.removeAt(position)
            taskAdapter.notifyItemRemoved(position)
        }
    }
}
