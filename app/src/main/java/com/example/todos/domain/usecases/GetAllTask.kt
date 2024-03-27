package com.example.todos.domain.usecases

import com.example.todos.dataLayer.localDatabase.TodoEntity
import com.example.todos.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetAllTask(private val repository: TodoRepository) {

    operator fun invoke():Flow<List<TodoEntity>>{
        return repository.getAllTask()
    }
}