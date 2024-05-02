package com.krkhvr.gamemvvmca.presentation.screens.myposts

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.krkhvr.gamemvvmca.presentation.components.DefaultTopBar
import com.krkhvr.gamemvvmca.presentation.navigation.DetailsScreens
import com.krkhvr.gamemvvmca.presentation.screens.myposts.components.MyPostsContent
import com.krkhvr.gamemvvmca.presentation.screens.myposts.components.MyPostsFlow

@Composable
fun MyPostsScreen(navHostController: NavHostController){
    Scaffold(
        content = {
            MyPostsFlow(paddingValues = it, navHostController = navHostController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(DetailsScreens.NewPostScreen.route)
                }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
    )
}