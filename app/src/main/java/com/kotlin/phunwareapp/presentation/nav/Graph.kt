package com.kotlin.phunwareapp.presentation.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kotlin.phunwareapp.data.utils.ResultAPI
import com.kotlin.phunwareapp.presentation.starwars.detail.DetailScreen
import com.kotlin.phunwareapp.presentation.starwars.master.MasterScreen
import com.kotlin.phunwareapp.presentation.starwars.master.StarWarsMasterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationGraph(viewModel: StarWarsMasterViewModel = koinViewModel()) {
    val navController = rememberNavController()

    viewModel.checkNetwork()
    val result by viewModel.events.observeAsState(initial = ResultAPI.Loading)
    if (result is ResultAPI.Error) {
        Box(
            modifier = Modifier
                .fillMaxSize(), // Fill the entire screen
            contentAlignment = Alignment.Center // Center the content
        ) {
            Text(
                text = "No network available. Please check your connection.",
                color = Color.Red
            )
        }    } else {
        NavHost(
            navController = navController,
            startDestination = Screen.MasterScreen.route
        ) {
            composable(Screen.MasterScreen.route) {
                MasterScreen(viewModel = viewModel, navController = navController)
            }
            composable(Screen.DetailScreen.route) { backStackEntry ->
                val eventId = backStackEntry.arguments?.getString("eventId")
                if (eventId != null) {
                    // Fetch the event using the eventId
                    val event =
                        viewModel.getEventById(eventId) // Implement this method in your ViewModel
                    if (event != null) {
                        DetailScreen(id = eventId, viewModel = viewModel)
                    } else {
                        // Handle the case where the event is not found
                        Text(text = "Event not found")
                    }
                }
            }
        }
    }
}
