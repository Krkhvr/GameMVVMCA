package com.krkhvr.gamemvvmca.presentation.screens.posts.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.krkhvr.gamemvvmca.domain.model.Post

@Composable
fun PostsContent(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    post: List<Post>
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues, )
            .fillMaxWidth()
    ){
        items(items = post){
           PostCard(navHostController, it)
        }
    }
}