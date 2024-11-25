package com.example.randomcatfacts.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.randomcatfacts.ui.fragments.CatFactScreen
import com.example.randomcatfacts.ui.fragments.CatPhotosScreen
import com.example.randomcatfacts.ui.fragments.MyCatsScreen
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(navController: NavController, modifier: Modifier = Modifier) {
    NavHost(
        navController = rememberNavController(),
        startDestination = "catFactScreen",
        modifier = modifier.fillMaxSize()
    ) {
        composable("catFactScreen") {
            CatFactScreen(navController = navController)
        }
        composable("catPhotosScreen") {
            CatPhotosScreen(navController = navController)
        }
        composable("myCatsScreen") {
            MyCatsScreen(navController = navController)
        }
    }
}
