package com.example.todolist.feature.home

sealed interface HomeEvent {
    data class Delete(val id: Long): HomeEvent

    data class AddEdit(val id: Long?) : HomeEvent
}