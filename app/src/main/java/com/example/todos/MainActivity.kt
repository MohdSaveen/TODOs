package com.example.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todos.presentation.Home.HomeScreen
import com.example.todos.presentation.Route
import com.example.todos.presentation.taskDetail.EditAndSaveScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Route.Home.route){
                composable(Route.Home.route) {
                    HomeScreen(navController = navController)
                }
                composable(route = "${Route.Detail.route}/{taskId}/{descId}/{flag}/{uid}",
                    arguments = listOf(
                        navArgument("taskId"){
                            type = NavType.StringType},
                        navArgument("descId"){
                            type = NavType.StringType},
                        navArgument("flag"){
                            type = NavType.BoolType },
                        navArgument("uid"){
                            type = NavType.IntType }
                    )
                ) { EditAndSaveScreen {
                    navController.popBackStack(Route.Home.route, false)} }
            }

        }
    }


}

