package com.example.todos.dataLayer.localDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract val todoDao: TodoDAO

    companion object {
        const val DATABASE_NAME = "todo_db"
    }
}