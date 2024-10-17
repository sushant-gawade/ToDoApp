package com.example.todoapp

data class Task(
    var taskName: String,   // The name of the task
    var isCompleted: Boolean = false  // Whether the task is completed or not
)
