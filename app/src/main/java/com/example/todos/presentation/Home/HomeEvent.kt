package com.example.todos.presentation.Home

import com.example.todos.dataLayer.localDatabase.TodoEntity

sealed class HomeEvent {


    data class DeleteTask(val todoEntity: TodoEntity) : HomeEvent()

}