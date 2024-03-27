package com.example.todos.domain.repository

import com.example.todos.dataLayer.localDatabase.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTask(todoEntity: TodoEntity)

    suspend fun deleteTask(todoEntity: TodoEntity)

    fun getAllTask(): Flow<List<TodoEntity>>

    suspend fun updateTask(todoEntity: TodoEntity)

}