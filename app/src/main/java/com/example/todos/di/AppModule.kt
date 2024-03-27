package com.example.todos.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todos.dataLayer.ToDoRepositoryImpl
import com.example.todos.dataLayer.localDatabase.TodoDAO
import com.example.todos.dataLayer.localDatabase.TodoDatabase
import com.example.todos.domain.repository.TodoRepository
import com.example.todos.domain.usecases.GetAllTask
import com.example.todos.domain.usecases.TodoUseCases
import com.example.todos.domain.usecases.deleteTask
import com.example.todos.domain.usecases.insertTask
import com.example.todos.domain.usecases.updateTask
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app : Application) : TodoDatabase{
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            TodoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(database: TodoDatabase) : TodoDAO{
        return database.todoDao
    }

    @Provides
    @Singleton
    fun provideTodoRepository(database: TodoDatabase) : TodoRepository{
        return ToDoRepositoryImpl(database.todoDao)
    }

    @Provides
    @Singleton
    fun provideTodoUseCases(repository: TodoRepository) : TodoUseCases{
        return TodoUseCases(
            getAllTask = GetAllTask(repository),
            insertTask = insertTask(repository),
            deleteTask = deleteTask(repository),
            updateTask = updateTask(repository)
        )
    }




}