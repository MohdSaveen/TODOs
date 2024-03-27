package com.example.todos.dataLayer.localDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val uid : Int? = null,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "description") val description : String
) {
}