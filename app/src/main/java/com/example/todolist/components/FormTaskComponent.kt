package com.example.todolist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.feature.addedit.AddEditEvent
import com.example.todolist.ui.theme.ConfirmGreen
import com.example.todolist.ui.theme.LightBlue

@Composable
@Preview
fun FormTaskComponent(title: String, body: String?, onEvent: (AddEditEvent) -> Unit) {
    Column(
        modifier = Modifier.background(Color.Transparent),
        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = title ,
            onValueChange = { onEvent(
                AddEditEvent.TitleChanged(it)
            ) },
            placeholder = {
                Text(
                    text = "Título",
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    color = Color.LightGray
                )
            },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = LightBlue,
                unfocusedLabelColor = Color.Gray,
                backgroundColor = Color.White
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value =body ?: "" ,
            onValueChange = { onEvent(
                AddEditEvent.BodyChanged(it)
            )

            },
            placeholder = {
                Text(
                    text = "Descrição",
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    color = Color.LightGray
                )
            },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = LightBlue,
                unfocusedLabelColor = Color.Gray,
                backgroundColor = Color.White
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value ="" ,
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Horário de início da atividade",
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    color = Color.LightGray
                )
            },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = LightBlue,
                unfocusedLabelColor = Color.Gray,
                backgroundColor = Color.White
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value ="" ,
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Horário de término da atividade",
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    color = Color.LightGray
                )
            },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = LightBlue,
                unfocusedLabelColor = Color.Gray,
                backgroundColor = Color.White
            )
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ConfirmGreen,
            )
        ) {
            Text(
                text = "Salvar",
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                color = Color.Black
            )
        }


    }
}