package com.krkhvr.gamemvvmca.presentation.screens.posts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.krkhvr.gamemvvmca.presentation.screens.posts.components.PostsFlow

@Composable
fun PostsScreen(navHostController: NavHostController){
    Scaffold(
        content = {
            //Text(modifier = Modifier.padding(it),text = "PostsScreen")
            PostsFlow(it, navHostController)
        }
    )

}