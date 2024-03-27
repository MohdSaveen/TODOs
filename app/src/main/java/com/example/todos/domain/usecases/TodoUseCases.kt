package com.example.todos.domain.usecases

data class TodoUseCases(
    val getAllTask: GetAllTask,
    val insertTask: insertTask,
    val deleteTask: deleteTask,
    val updateTask: updateTask
)