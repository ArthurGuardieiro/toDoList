package com.example.todolist.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TaskRepository
import com.example.todolist.navigation.AddEditRoute
import com.example.todolist.ui.theme.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TaskRepository,
) : ViewModel() {

    val todos = repository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _uiEvent = Channel<UiEvent> {  }
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HomeEvent) {
        when(event){
            is HomeEvent.Delete -> {
                delete(event.id)
            }

            is HomeEvent.AddEdit -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(AddEditRoute(event.id)))
                }
            }

            is HomeEvent.CompleteChanged -> {
                completeChanged(event.id, event.isCompleted)
            }
        }
    }

    private fun delete(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    private fun completeChanged(id: Long, completed: Boolean) {
        viewModelScope.launch {
            repository.updateCompleted(id, completed)
        }
    }


}