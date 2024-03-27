package com.example.todos.presentation.taskDetail

data class AddAndEditTextFields(
    var text : String = "",
    var hint : String  = "",
    var isHintVisible :Boolean = true,
    var isFlag : Boolean = false
)
