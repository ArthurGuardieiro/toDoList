package com.example.todolist.data

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int,
    val title: String,
    val body: String? = null,
    val startTime: String,
    val endTime: String
)

val taskList = listOf(
    Task(
        1,
        "Lavar roupas",
        "Colocar as roupas na máquina de lavar",
        "10:00",
        "11:00"
    ),
    Task(
        2,
        "Limpar a cozinha",
        "Limpar os talheres, pratos e o chão",
        "11:30",
        "12:30"
    )

)