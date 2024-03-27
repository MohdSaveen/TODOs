package com.example.todos.presentation.Home

import com.example.todos.dataLayer.localDatabase.TodoEntity

data class HomeState(
    var tasks : List<TodoEntity> = listOf(),
    var addTitle : String = "",
    val addDescription : String = "",
    var isShowDialog : Boolean = false


)