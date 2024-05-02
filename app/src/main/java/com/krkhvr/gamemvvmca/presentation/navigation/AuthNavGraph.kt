package com.krkhvr.gamemvvmca.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.krkhvr.gamemvvmca.presentation.screens.login.LoginScreen
import com.krkhvr.gamemvvmca.presentation.screens.signup.SignupScreen

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController){
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreens.LoginScreen.route
    ){
        composable(route = AuthScreens.LoginScreen.route) {
            LoginScreen(navHostController)
        }

        composable(route = AuthScreens.SignupScreen.route) {
            SignupScreen(navHostController)
        }
    }
}

sealed class AuthScreens(val route: String) {
    data object LoginScreen: AuthScreens("loginScreen")
    data object SignupScreen: AuthScreens("signupScreen")
}