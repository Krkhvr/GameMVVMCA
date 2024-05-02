package com.krkhvr.gamemvvmca.presentation.screens.postdetail

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.krkhvr.gamemvvmca.presentation.screens.postdetail.components.PostDetailContent

@Composable
fun PostDetailScreen(
    navHostController: NavHostController
) {
    Scaffold(
        content = {
            PostDetailContent(it, navHostController)
        }
    )
}