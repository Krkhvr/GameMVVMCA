package com.krkhvr.gamemvvmca.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.krkhvr.gamemvvmca.presentation.screens.myposts.MyPostsScreen
import com.krkhvr.gamemvvmca.presentation.screens.posts.PostsScreen
import com.krkhvr.gamemvvmca.presentation.screens.profile.ProfileScreen

@Composable
fun HomeBottomBarNavGraph(navHostController: NavHostController, paddingValues: PaddingValues){
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navHostController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreens.Posts.route
    ){

        composable(route = HomeBottomBarScreens.Posts.route) {
            PostsScreen(navHostController)
        }

        composable(route = HomeBottomBarScreens.MyPosts.route) {
            MyPostsScreen(navHostController)
        }

        composable(route = HomeBottomBarScreens.Profile.route) {
            ProfileScreen(navHostController)
        }

        detailsNavGraph(navHostController)
    }
}

sealed class HomeBottomBarScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Posts : HomeBottomBarScreens(
        route = "posts",
        title = "Posts",
        icon = Icons.Default.List
    )

    data object MyPosts : HomeBottomBarScreens(
        route = "myPosts",
        title = "Mis posts",
        icon = Icons.Outlined.List
    )

    data object Profile : HomeBottomBarScreens(
        route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )
}