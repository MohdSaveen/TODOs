package com.example.todos.presentation

sealed class Route(val route : String) {
    data object Home:Route("HomeScreen")
    data object Detail: Route("DetailScreen")
}