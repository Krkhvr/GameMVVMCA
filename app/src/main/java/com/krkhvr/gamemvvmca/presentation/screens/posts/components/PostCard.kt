package com.krkhvr.gamemvvmca.presentation.screens.posts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.krkhvr.gamemvvmca.R
import com.krkhvr.gamemvvmca.domain.model.Post
import com.krkhvr.gamemvvmca.presentation.navigation.DetailsScreens
import com.krkhvr.gamemvvmca.presentation.screens.posts.PostsViewModel

@Composable
fun PostCard(
    navHostController: NavHostController,
    post: Post,
    viewModel: PostsViewModel = hiltViewModel()) {
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
                text = post.user?.username ?: "",
                fontSize = 12.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 3.dp),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )

            Row(
                modifier = Modifier.padding(start = 15.dp, bottom = 10.dp)
            ){
                if(post.likes.contains(viewModel.currentUser?.uid ?: "")){
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                viewModel.dislikePost(
                                    post.id,
                                    viewModel.currentUser?.uid ?: ""
                                )
                            },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = "",
                    )
                }else{
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                viewModel.likePost(
                                    post.id,
                                    viewModel.currentUser?.uid ?: ""
                                )
                            },
                        painter = painterResource(id = R.drawable.like_outline),
                        contentDescription = "",
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = post.likes.size.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        }
    }
}