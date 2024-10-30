package com.kotlin.phunwareapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kotlin.phunwareapp.data.utils.ResultAPI
import com.kotlin.phunwareapp.data.utils.ResultAPI.*
import com.kotlin.phunwareapp.domain.model.StarWarsEventItem
import com.kotlin.phunwareapp.presentation.starwars.master.EventList
import com.kotlin.phunwareapp.presentation.starwars.master.StarWarsMasterViewModel
import com.kotlin.phunwareapp.ui.theme.PhunwareAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhunwareAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: StarWarsMasterViewModel = koinViewModel()) {    // Observe events as state
    viewModel.fetchStarWarsEvents()
    val result by viewModel.events.observeAsState(initial = Loading)
    when (result) {
        is Success<*> -> {
            val events = (result as Success<List<StarWarsEventItem>>).data

            events?.let {
                EventList(events = it, onEventClick = { clickedEvent ->
                    // Show a toast with the event ID when the card is clicked
                    Toast.makeText(LocalContext.current, "Clicked event ID: ${clickedEvent.id}", Toast.LENGTH_SHORT).show()
                })
            }

            events?.forEach { event ->
                println("Event title: ${event.title}")

            }
        }
        is Error -> {
            // Handle error
            println("Error: ${(result as Error).message}")
        }
        is Loading -> {
            // Show loading indicator
            println("Loading...")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhunwareAppTheme {
        Greeting()
    }
}
