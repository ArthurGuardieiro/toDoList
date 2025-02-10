package com.example.todolist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
@Preview
fun WelcomeMessageComponent(quantity: Int) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Olá Neymar!",
            fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
            fontSize = 22.sp
        )
        Text(
            text = quantityMessage(quantity),
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            fontSize = 18.sp,
            color = Color.LightGray
        )
    }
}

fun quantityMessage(quantity: Int) : String {
    val hoje = LocalDate.now()
    val formato = DateTimeFormatter.ofPattern("EEEE", Locale("pt", "BR"))
    val diaSemana = hoje.format(formato).replaceFirstChar { it.uppercase() }
    if (quantity == 0) {
        return "Nenhuma tarefa para hoje $diaSemana"
    } else if (quantity == 1) {
        return "1 tarefa para hoje $diaSemana"
    }
    return "$quantity tarefas para hoje $diaSemana"
}