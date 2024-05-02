package com.krkhvr.gamemvvmca.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.krkhvr.gamemvvmca.presentation.screens.editprofile.EditProfileScreen
import com.krkhvr.gamemvvmca.presentation.screens.newposts.NewPostScreen
import com.krkhvr.gamemvvmca.presentation.screens.postdetail.PostDetailScreen
import com.krkhvr.gamemvvmca.presentation.screens.updatepost.UpdatePostScreen

fun NavGraphBuilder.detailsNavGraph(navHostController: NavHostController) {

    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreens.EditProfileScreen.route
    ) {

        composable(route = DetailsScreens.NewPostScreen.route) {
            NewPostScreen(navHostController = navHostController)
        }

        composable(
            route = DetailsScreens.EditProfileScreen.route,
            arguments = listOf(navArgument("user") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("user")?.let { user ->
                EditProfileScreen(navHostController, user)
            }
        }

        composable(
            route = DetailsScreens.PostDetailScreen.route,
            arguments = listOf(navArgument("post") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("post")?.let { post ->
                //PostDetailScreen(navHostController, post)
                PostDetailScreen(navHostController)
            }
        }

        composable(
            route = DetailsScreens.UpdatePostScreen.route,
            arguments = listOf(navArgument("post") {
                type = NavType.StringType
            })
        ) {
            UpdatePostScreen(navHostController)
        }
    }
}

sealed class DetailsScreens(val route: String) {
    data object NewPostScreen : DetailsScreens("posts/new")
    data object EditProfileScreen : DetailsScreens("profile/edit/{user}") {
        fun setUser(user: String) = "profile/edit/$user"
    }

    data object PostDetailScreen : DetailsScreens("post/detail/{post}") {
        fun setPost(post: String) = "post/detail/$post"
    }

    data object UpdatePostScreen : DetailsScreens("post/update/{post}") {
        fun setPost(post: String) = "post/update/$post"
    }
}