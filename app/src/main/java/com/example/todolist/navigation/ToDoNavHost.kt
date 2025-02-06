package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.todolist.feature.addedit.AddEditScreen
import com.example.todolist.feature.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object ListRoute

@Serializable
data class AddEditRoute(val id: Long? = null)

@Composable
fun ToDoNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListRoute) {
        composable<ListRoute> {
            HomeScreen(
                navigateToAddEditScreen = { id ->
                    navController.navigate(AddEditRoute(id = id))
                }
            )
        }

        composable<AddEditRoute> { navBackStackEntry ->
            val addEditRoute = navBackStackEntry.toRoute<AddEditRoute>()
            AddEditScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
