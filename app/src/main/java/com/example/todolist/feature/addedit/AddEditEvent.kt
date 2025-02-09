package com.example.todolist.feature.addedit

sealed interface AddEditEvent {
    data class TitleChanged(val title: String): AddEditEvent
    data class BodyChanged(val description: String): AddEditEvent
    data class StartChanged(val startTime: String): AddEditEvent
    data class EndChanged(val endTime: String): AddEditEvent
    data object  Save: AddEditEvent
}