package com.example.todos.domain.usecases

import com.example.todos.dataLayer.localDatabase.TodoEntity
import com.example.todos.domain.repository.TodoRepository

class deleteTask(val repository: TodoRepository) {

    suspend operator fun invoke(todoEntity: TodoEntity){
        return repository.deleteTask(todoEntity)
    }
}