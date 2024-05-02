package com.krkhvr.gamemvvmca.presentation.screens.newposts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.krkhvr.gamemvvmca.presentation.components.DefaultButton
import com.krkhvr.gamemvvmca.presentation.components.DefaultTopBar
import com.krkhvr.gamemvvmca.presentation.screens.newposts.components.NewPostContent
import com.krkhvr.gamemvvmca.presentation.screens.newposts.components.NewPostsFlow
import com.krkhvr.gamemvvmca.presentation.ui.theme.Red500

@Composable
fun NewPostScreen(
    navHostController: NavHostController,
    newPostViewModel: NewPostViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo Post",
                showArrow = true,
                navHostController = navHostController
            )
        },
        content = {
            NewPostContent(it)
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                text = "PUBLICAR",
                color = Red500,
                icon = Icons.AutoMirrored.Filled.ArrowForward
            ) {
                newPostViewModel.createPost()
            }
        }
    )
    NewPostsFlow()
}