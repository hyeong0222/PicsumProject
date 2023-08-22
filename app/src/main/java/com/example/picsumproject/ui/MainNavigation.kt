package com.example.picsumproject.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.picsumproject.navigation.Screens

@Composable
internal fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.MAIN(),
        builder = {
            composable(Screens.MAIN()) { MainScreen(navController = navController) }
            composable(Screens.DETAIL()) { DetailScreen() }
        }
    )
}

fun NavController.navigateToDetail() {
    this.navigate(Screens.DETAIL())
}