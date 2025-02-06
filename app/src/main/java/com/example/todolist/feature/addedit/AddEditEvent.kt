package com.example.todolist.feature.addedit

sealed interface AddEditEvent {
    data class TitleChanged(val title: String): AddEditEvent
    data class BodyChanged(val description: String): AddEditEvent
    data class StartChanged(val description: String): AddEditEvent
    data class EndChanged(val description: String): AddEditEvent
    data object  Save: AddEditEvent
}