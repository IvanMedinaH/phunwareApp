package com.kotlin.phunwareapp.presentation.starwars.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.kotlin.phunwareapp.R
import com.kotlin.phunwareapp.data.utils.formatToUserTimeZone
import com.kotlin.phunwareapp.domain.model.StarWarsEventItem
import com.kotlin.phunwareapp.presentation.starwars.master.StarWarsMasterViewModel


@Composable
fun DetailScreen(id: String, viewModel: StarWarsMasterViewModel) {
    val event = viewModel.getEventById(id)
    val context  = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                val imagePainter = rememberImagePainter(
                    data = event?.image,
                    builder = {
                        placeholder(R.drawable.placeholder_nomoon)
                        error(R.drawable.placeholder_nomoon)
                    }
                )
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.45f)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(30.dp)
            ) {
                event?.date?.let {
                    val formattedDate = formatToUserTimeZone(it)
                    Text(
                        text = formattedDate,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White
                    )
                }
                event?.let {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.White
                    )
                }
                event?.locationline1.let {
                    it?.let { it1 ->
                        Text(
                            text = it1,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White
                        )
                    }
                }
                event?.description?.let { description ->
                    Text(
                        text = description,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        modifier = Modifier
                            .height(120.dp)
                            .verticalScroll(rememberScrollState()) // Make the Text scrollable
                            .padding(16.dp) // Optional: Add some padding for better visual
                    )
                }

            Spacer(modifier = Modifier.height(5.dp))

            // Share buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(onClick = { event?.let { shareViaSMS(it, context) } }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id =  R.drawable.ic_sms),
                            contentDescription = "Share via SMS",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    IconButton(onClick = { event?.let { shareViaEmail(it, context) }}) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Share via Email",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }
    }
}

fun shareViaSMS(event: StarWarsEventItem, context: Context) {
    val smsBody = "Check out this event: ${event.title}\n${event.description ?: ""}"

    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("smsto:") // Only SMS apps should handle this
        putExtra("sms_body", smsBody)
    }

    context.startActivity(intent)
}

fun shareViaEmail(event: StarWarsEventItem, context: Context) {
    val emailSubject = "Check out this event: ${event.title}"
    val emailBody = "${event.description ?: ""}\n\nDate: ${event.date ?: ""}"

    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:") // Only email apps should handle this
        putExtra(Intent.EXTRA_SUBJECT, emailSubject)
        putExtra(Intent.EXTRA_TEXT, emailBody)
    }

    context.startActivity(intent)
}



