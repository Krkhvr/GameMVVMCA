package com.krkhvr.gamemvvmca.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.krkhvr.gamemvvmca.presentation.screens.editprofile.EditProfileScreen
import com.krkhvr.gamemvvmca.presentation.screens.home.HomeScreen
import com.krkhvr.gamemvvmca.presentation.screens.profile.ProfileScreen

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {

        authNavGraph(navHostController = navHostController)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}