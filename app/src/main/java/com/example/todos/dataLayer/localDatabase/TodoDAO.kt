package com.example.todos.dataLayer.localDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(todoEntity: TodoEntity)

    @Delete
   suspend fun deleteTask(todoEntity: TodoEntity)

   @Update
   suspend fun updateTask(todoEntity: TodoEntity)

   @Query("select * from todos")
   fun getAllTask(): Flow<List<TodoEntity>>

}