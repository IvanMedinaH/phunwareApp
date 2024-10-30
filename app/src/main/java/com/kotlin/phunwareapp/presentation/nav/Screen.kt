package com.kotlin.phunwareapp.presentation.nav

sealed class Screen(val route: String) {
    object MasterScreen : Screen("master")
    object DetailScreen : Screen("detail/{eventId}") {
        fun createRoute(eventId: String) = "detail/$eventId"
    }
}