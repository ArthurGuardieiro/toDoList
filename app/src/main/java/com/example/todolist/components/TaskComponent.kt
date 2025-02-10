package com.example.todolist.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.data.Task
import com.example.todolist.feature.home.HomeEvent
import com.example.todolist.ui.theme.LightBlue
import com.example.todolist.ui.theme.LightGreen
import com.example.todolist.ui.theme.LightPurple
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TaskComponent(
    task: Task,
    onItemClick: () -> Unit,
    onDeletClick: () -> Unit
) {
    val taskColor = listOf(LightPurple, LightBlue, LightGreen).random()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = converterParaAMPM(task.startTime),
            fontFamily = FontFamily(Font(R.font.nunito_bold)),
            textAlign = TextAlign.Center
        )

        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(3.dp, Color.Black),
                        shape = CircleShape
                    )
            )

            Divider(modifier = Modifier.width(6.dp), color = Color.Black)



            Row(
                modifier = Modifier.fillMaxWidth()
                    .clickable { onItemClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(taskColor)
                        .weight(0.9f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = task.title,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        modifier = Modifier
                            .padding(
                                top = 12.dp,
                                start = 12.dp
                            )
                    )

                    if(task.body != null) {
                        Text(
                            text = task.body,
                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
                            modifier = Modifier
                                .padding(start = 12.dp),
                            color = Color.LightGray
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${task.startTime} - ${converterParaAMPMSemQuebra(task.endTime)}",
                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
                            modifier = Modifier
                                .padding(
                                    start = 12.dp,
                                    bottom = 12.dp
                                ),

                            )
                        IconButton(
                            onClick =  onDeletClick,
                            modifier = Modifier.padding(end = 10.dp)
                        ){
                            Icon(imageVector = Icons.Filled.Delete,
                                contentDescription = "Deletar"
                            )
                        }

                    }


                }

                Divider(
                    modifier = Modifier
                        .width(6.dp)
                        .weight(0.1f),
                    color = Color.Black
                )

            }
        }


    }
}

fun converterParaAMPM(horas24: String): String {
    return try {
        val formato24h = DateTimeFormatter.ofPattern("HH:mm")
        val formato12h = DateTimeFormatter.ofPattern("hh:mm a", Locale("en", "US")) // AM/PM

        val hora = LocalTime.parse(horas24, formato24h)
        val formatoFinal = hora.format(formato12h).replace(" ", "\n") // Adiciona quebra de linha antes de AM/PM
        formatoFinal
    } catch (e: Exception) {
        "Formato inválido"
    }
}

fun converterParaAMPMSemQuebra(horas24: String): String {
    return try {
        val formato24h = DateTimeFormatter.ofPattern("HH:mm")
        val formato12h = DateTimeFormatter.ofPattern("hh:mm a", Locale("en", "US")) // AM/PM

        val hora = LocalTime.parse(horas24, formato24h)
        hora.format(formato12h)
    } catch (e: Exception) {
        "Formato inválido"
    }
}