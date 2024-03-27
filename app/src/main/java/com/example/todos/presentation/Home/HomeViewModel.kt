package com.example.todos.presentation.Home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todos.dataLayer.localDatabase.TodoEntity
import com.example.todos.domain.usecases.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase : TodoUseCases): ViewModel(){

    private var _uiState = MutableStateFlow(HomeState())
    val uiState : StateFlow<HomeState> = _uiState.asStateFlow()


    init {

        getAllTask()
        Log.d("TAG", ":task has started ")
    }

    private fun getAllTask(){
        viewModelScope.launch {
            useCase.getAllTask.invoke().onEach { list ->
                _uiState.update {
                    it.copy(tasks = list)
                }
            }.collect()
        }

    }

    fun onEvent(event: HomeEvent) {
        when(event){

            is HomeEvent.DeleteTask -> viewModelScope.launch {
                useCase.deleteTask(event.todoEntity)
            }

        }
    }

}