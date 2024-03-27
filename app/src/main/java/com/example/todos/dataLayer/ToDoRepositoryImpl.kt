package com.example.todos.dataLayer

import com.example.todos.dataLayer.localDatabase.TodoDAO
import com.example.todos.dataLayer.localDatabase.TodoEntity
import com.example.todos.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDoRepositoryImpl(private val todoDao : TodoDAO) : TodoRepository {

    override suspend fun insertTask(todoEntity: TodoEntity) {
        return todoDao.insertTask(todoEntity)
    }

    override suspend fun deleteTask(todoEntity: TodoEntity) {
        return todoDao.deleteTask(todoEntity)
    }

    override fun getAllTask() : Flow<List<TodoEntity>>{
        return todoDao.getAllTask()
    }

    override suspend fun updateTask(todoEntity: TodoEntity) {
        return todoDao.updateTask(todoEntity)
    }

}