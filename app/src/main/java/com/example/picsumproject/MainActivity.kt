package com.example.picsumproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.picsumproject.ui.MainNavigation
import com.example.picsumproject.ui.MainScreen
import com.example.picsumproject.ui.theme.PicsumProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicsumProjectTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    MainNavigation(navController = rememberNavController())
                }
            }
        }
    }
}