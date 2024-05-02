package com.krkhvr.gamemvvmca.presentation.screens.myposts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.krkhvr.gamemvvmca.domain.model.Post
import com.krkhvr.gamemvvmca.presentation.navigation.DetailsScreens
import com.krkhvr.gamemvvmca.presentation.screens.myposts.MyPostsViewModel

@Composable
fun MyPostCard(
    navHostController: NavHostController,
    post: Post,
    viewModel: MyPostsViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .padding(start = 15.dp, top = 15.dp, end = 15.dp)
            .clickable {
                navHostController.navigate(
                    route = DetailsScreens.PostDetailScreen.setPost(post.toJson())
                )
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White //Card background color
            //contentColor = Color.White  //Card content color,e.g.text
        ),
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                model = post.image,
                contentDescription = post.description,
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                text = post.name,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 3.dp),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        navHostController.navigate(
                            route = DetailsScreens.UpdatePostScreen.setPost(post.toJson())
                        )
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Edit,
                        contentDescription = "",
                        tint = Color.White
                    )
                }

                IconButton(
                    onClick = { viewModel.deletePost(post.id) }
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    }
}