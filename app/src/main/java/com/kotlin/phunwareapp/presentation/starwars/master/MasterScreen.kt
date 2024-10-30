package com.kotlin.phunwareapp.presentation.starwars.master

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.kotlin.phunwareapp.R
import com.kotlin.phunwareapp.data.utils.ResultAPI.Error
import com.kotlin.phunwareapp.data.utils.ResultAPI.Loading
import com.kotlin.phunwareapp.data.utils.ResultAPI.Success
import com.kotlin.phunwareapp.data.utils.formatToUserTimeZone
import com.kotlin.phunwareapp.data.utils.truncateDescription
import com.kotlin.phunwareapp.domain.model.StarWarsEventItem
import com.kotlin.phunwareapp.presentation.nav.Screen
import org.koin.androidx.compose.koinViewModel


@Composable
fun MasterScreen(viewModel: StarWarsMasterViewModel = koinViewModel(), navController: NavController) {    // Observe events as state
    viewModel.fetchStarWarsEvents()
    val result by viewModel.events.observeAsState(initial = Loading)
    when (result) {
        is Success<*> -> {
            val events = (result as Success<List<StarWarsEventItem>>).data

            events?.let {
                EventList(events = it, onEventClick = { clickedEvent ->
                    navController.navigate(Screen.DetailScreen.createRoute(clickedEvent.id.toString()))
                })
            }

            events?.forEach { event ->
                println("Event title: ${event.title}")
            }
        }
        is Error -> {

            println("Error: ${(result as Error.ServerError)}")
        }
        is Loading -> {

            println("Loading...")
        }
    }
}


@Composable
fun EventList(events: List<StarWarsEventItem>, onEventClick: (StarWarsEventItem) -> Unit) {
    val context = LocalContext.current
    LazyColumn {
        items(events) { event ->
            EventCardItem(event = event, onClick = {
                onEventClick(event)
               // Toast.makeText(context, "Clicked event ID: ${event.id}", Toast.LENGTH_SHORT).show()
            })
        }
    }
}


@Composable
fun EventCardItem(event: StarWarsEventItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        ) {
            val imagePainter = rememberImagePainter(
                data = event.image,
                builder = {
                    placeholder(R.drawable.placeholder_nomoon)
                    error(R.drawable.placeholder_nomoon)
                }
            )

            // Display the image
            Image(
                painter = imagePainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(14.dp)
                ) {
                    var date = event.date?.let { formatToUserTimeZone(it) }
                    date?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White // Cambiar el color del texto a blanco
                        )
                    }
                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.White // Cambiar el color del texto a blanco
                    )

                    event.locationline1?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White // Cambiar el color del texto a blanco
                        )
                    }

                    event.description?.let {
                        Text(
                            text = truncateDescription(it, 11),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White // Cambiar el color del texto a blanco
                        )
                    }
                }
            }
        }
    }
}




