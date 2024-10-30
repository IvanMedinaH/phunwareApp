package com.kotlin.phunwareapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.kotlin.phunwareapp.presentation.nav.NavigationGraph
import com.kotlin.phunwareapp.ui.theme.PhunwareAppTheme
import com.kotlin.phunwareapp.ui.theme.darkerGrey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhunwareAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = darkerGrey
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}



