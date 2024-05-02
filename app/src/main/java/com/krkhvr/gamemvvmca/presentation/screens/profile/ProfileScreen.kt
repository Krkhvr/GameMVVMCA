package com.krkhvr.gamemvvmca.presentation.screens.profile

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.krkhvr.gamemvvmca.presentation.screens.profile.components.ProfileContent
import com.krkhvr.gamemvvmca.presentation.screens.signup.components.SignupFlow

@Composable
fun ProfileScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {},
        content = {
                  ProfileContent(it, navHostController)
        },
        bottomBar = {}
    )
}