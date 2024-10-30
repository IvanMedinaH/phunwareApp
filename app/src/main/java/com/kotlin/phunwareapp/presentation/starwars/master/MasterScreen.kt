package com.kotlin.phunwareapp.presentation.starwars.master

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kotlin.phunwareapp.data.utils.ResultAPI
import com.kotlin.phunwareapp.domain.model.StarWarsEventItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun EventList(events: List<StarWarsEventItem>, onEventClick: @Composable (StarWarsEventItem) -> Unit) {
    val context = LocalContext.current // Get the current context
    LazyColumn {
        items(events) { event ->
            EventCard(event = event, onClick = {
                // Show a toast with the event ID when the card is clicked
                Toast.makeText(context, "Clicked event ID: ${event.id}", Toast.LENGTH_SHORT).show()
            })
        }
    }
}

@Composable
fun EventCard(event: StarWarsEventItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick),  // Make the card clickable
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )

            Text(
                text = event.date.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )

            event.locationline1?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }

            event.locationline2?.let {
                Text(
                    text = it
                )
            }
        }
    }
}



