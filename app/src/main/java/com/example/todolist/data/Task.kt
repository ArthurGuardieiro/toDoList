package com.example.todolist.data

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Long,
    val title: String,
    val body: String? = null,
    val startTime: String,
    val endTime: String,
    val isCompleted: Boolean
)

