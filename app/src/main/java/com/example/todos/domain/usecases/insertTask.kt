package com.example.todos.domain.usecases

import com.example.todos.dataLayer.localDatabase.TodoEntity
import com.example.todos.domain.repository.TodoRepository
import kotlin.jvm.Throws


class insertTask(private val repository: TodoRepository) {



    suspend operator fun invoke(todoEntity: TodoEntity){
        return repository.insertTask(todoEntity)
    }


}