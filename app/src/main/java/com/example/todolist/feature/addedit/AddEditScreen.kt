package com.example.todolist.feature.addedit

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.components.AddMessageComponent
import com.example.todolist.components.FormTaskComponent
import com.example.todolist.components.ProfileHeaderComponent
import com.example.todolist.data.TaskDatabase
import com.example.todolist.data.TaskDatabaseProvider
import com.example.todolist.data.TaskRepositoryImpl
import com.example.todolist.ui.theme.ToDoListTheme
import com.example.todolist.ui.theme.UiEvent

@Composable
fun AddEditScreen(
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current.applicationContext
    val database = TaskDatabaseProvider.provide(context)
    val repository = TaskRepositoryImpl(
        dao = database.taskDao
    )
    val viewModal = viewModel<AddEditViewModel> {
        AddEditViewModel(repository = repository)
    }
    
    val title = viewModal.title
    val description = viewModal.description
    val startTime = viewModal.startTime
    val endTime = viewModal.endTime

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        viewModal.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                    )
                }
                UiEvent.NavigateBack -> {
                    navigateBack()
                }
                is UiEvent.Navigate<*> -> {

                }
            }
        }
    }

    AddEditContent(
        title = title,
        description = description,
        startTime = startTime,
        endTime = endTime,
        snackbarHostState = snackbarHostState,
        onEvent = viewModal::onEvent,
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditContent(
    title: String,
    description: String?,
    startTime: String,
    endTime: String,
    snackbarHostState: SnackbarHostState,
    onEvent: (AddEditEvent) -> Unit,
){
    var selectedScreen by remember {
        mutableStateOf(1)
    }
    val screens = listOf("Calendar", "Home", "Notifications")
    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.height(90.dp),
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                screens.forEachIndexed { index, _ ->
                    val icon: ImageVector = when(index) {
                        0 -> Icons.Filled.CalendarMonth
                        1 -> Icons.Filled.Home
                        2 -> Icons.Filled.Mail
                        else -> Icons.Filled.Home
                    }
                    BottomNavigationItem(
                        selected = selectedScreen == index,
                        onClick = { selectedScreen = index },
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(if (selectedScreen == index) Color.Black else Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Screen",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(12.dp),
                                    tint = if (selectedScreen == index) Color.White else Color.Black
                                )
                            }
                        },

                    )


                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                bottom = 16.dp,
                end = 16.dp
            )

        ) {
            item {
                ProfileHeaderComponent()
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))

                AddMessageComponent()

                Spacer(modifier = Modifier.height(30.dp))
            }

            item {
                FormTaskComponent(
                    title, description, startTime, endTime, onEvent
                )
            }



        }

    }

}

@Composable
@Preview
private fun AddEdditContentPreview(){
    ToDoListTheme {
        AddEditContent(
            title = "",
            description = null,
            startTime = "",
            endTime = "",
            snackbarHostState = SnackbarHostState(),
            onEvent = {}
        )
    }
}