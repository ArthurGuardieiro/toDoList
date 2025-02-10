package com.example.todolist.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.todolist.components.ProfileHeaderComponent
import com.example.todolist.components.TaskComponent
import com.example.todolist.components.WelcomeMessageComponent
import com.example.todolist.data.Task
import com.example.todolist.data.TaskDatabaseProvider
import com.example.todolist.data.TaskRepositoryImpl
import com.example.todolist.data.taskList
import com.example.todolist.feature.addedit.AddEditViewModel
import com.example.todolist.ui.theme.ToDoListTheme

@Composable
fun HomeScreen(
    navigateToAddEditScreen: (id: Long?) -> Unit
) {

    val context = LocalContext.current.applicationContext
    val database = TaskDatabaseProvider.provide(context)
    val repository = TaskRepositoryImpl(
        dao = database.taskDao
    )
    val viewModel = viewModel<HomeViewModel> {
        HomeViewModel(repository = repository)
    }

    val todos by viewModel.todos.collectAsState()

    HomeContent(
        todos = todos,
        onAddItemClick = navigateToAddEditScreen
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeContent(
    todos: List<Task>,
    onAddItemClick: (id: Long?) -> Unit,
) {
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
                        }
                    )


                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick =  {onAddItemClick(null)} ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                bottom = 16.dp
            )

        ) {
            item {
                ProfileHeaderComponent()
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))

                WelcomeMessageComponent()

                Spacer(modifier = Modifier.height(30.dp))
            }

            items(todos) { task ->
                TaskComponent(task = task)

                Spacer(modifier = Modifier.height(16.dp))
            }

        }

    }
}

@Composable
@Preview
private fun HomeScreenContentPreview() {
    ToDoListTheme {
        HomeContent(
            todos = listOf(),
            onAddItemClick = {}
        )
    }
}