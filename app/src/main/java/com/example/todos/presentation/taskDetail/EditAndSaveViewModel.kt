package com.example.todos.presentation.taskDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todos.dataLayer.localDatabase.TodoEntity
import com.example.todos.domain.usecases.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAndSaveViewModel @Inject constructor(
    private val useCases: TodoUseCases,
     savedStateHandle: SavedStateHandle) : ViewModel() {

    private var _title = mutableStateOf(AddAndEditTextFields())
    val title = _title

    private var _des = mutableStateOf(AddAndEditTextFields())
    val desc = _des

    val flag = savedStateHandle.get<Boolean>("flag")
    private val cardUid = savedStateHandle.get<Int>("uid")

    init {
        val titleId = savedStateHandle.get<String>("taskId")
        val descId = savedStateHandle.get<String>("descId")



        if (flag == true) {
            if (titleId != null) {
                title.value.text = titleId
            }
            if (descId != null) {
                desc.value.text = descId
            }
            title.value.isHintVisible = false
            title.value.hint=""
            desc.value.hint=""
            desc.value.isHintVisible = false
        }


    }
    fun onEvent(events: EditAndSaveEvents){
        when(events){
            is EditAndSaveEvents.EnteredDes ->
                _des.value = desc.value.copy(
                    text =
                    events.input
                )
            is EditAndSaveEvents.EnteredTask -> _title.value = title.value.copy(
                text =
                events.input
            )
            EditAndSaveEvents.SaveTask ->
                try {
                    viewModelScope.launch {
                        if (flag == false) {
                            useCases.insertTask.invoke(
                                TodoEntity(
                                    text = title.value.text,
                                    description = desc.value.text
                                )
                            )
                        }else if (flag == true){
                            useCases.updateTask.invoke(
                                TodoEntity(
                                    text = title.value.text,
                                    description = desc.value.text,
                                    uid = cardUid
                                )
                            )
                        }
                    }
                }catch (e: Exception){
                    e.localizedMessage
                }
        }
    }

}