package com.example.todos.presentation.taskDetail

import androidx.compose.ui.focus.FocusState

sealed class EditAndSaveEvents {

    data class EnteredTask(val input : String) : EditAndSaveEvents()
    data class EnteredDes(val input: String) : EditAndSaveEvents()
    data object SaveTask : EditAndSaveEvents()



}