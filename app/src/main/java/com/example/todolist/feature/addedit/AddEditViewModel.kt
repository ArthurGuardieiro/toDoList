package com.example.todolist.feature.addedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TaskRepository
import com.example.todolist.ui.theme.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditViewModel (
    private val id: Long? = null,
    private val repository: TaskRepository,
) : ViewModel() {

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf<String?>(null)
        private set

    var startTime by mutableStateOf<String>("")
        private set

    var endTime by mutableStateOf<String>("")
        private set


    private val _uiEvent = Channel<UiEvent> {  }
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
       id?.let {
           viewModelScope.launch {
               val todo = repository.getBy(it)
               title = todo?.title ?: ""
               description = todo?.body
               startTime = todo?.startTime ?: ""
               endTime = todo?.endTime ?: ""
           }
       }
    }

    fun onEvent(event: AddEditEvent){
        when (event){
            is AddEditEvent.TitleChanged -> {
                title = event.title
            }
            is AddEditEvent.BodyChanged -> {
                description = event.description
            }
            is AddEditEvent.StartChanged -> {
                startTime = event.startTime
            }
            is AddEditEvent.EndChanged -> {
                endTime = event.endTime
            }
            is AddEditEvent.Save ->{
                save()
            }
        }
    }


    private fun save() {
        viewModelScope.launch {
            if(title.isBlank()){
                _uiEvent.send(UiEvent.ShowSnackbar("Title cannot be empty"))
                return@launch
            }

            repository.insert(title, description, startTime, endTime, id)
            _uiEvent.send(UiEvent.NavigateBack)
        }

    }

}

