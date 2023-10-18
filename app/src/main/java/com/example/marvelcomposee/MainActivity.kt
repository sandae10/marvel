package com.example.marvelcomposee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.marvelcomposee.ui.navigation.Navigation
import com.example.marvelcomposee.ui.theme.MarvelScreen

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelScreen {
                Navigation(navController)
            }
        }
    }
}




