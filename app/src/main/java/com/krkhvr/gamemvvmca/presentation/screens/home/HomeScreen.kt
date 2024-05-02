package com.krkhvr.gamemvvmca.presentation.screens.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.krkhvr.gamemvvmca.presentation.navigation.HomeBottomBarNavGraph
import com.krkhvr.gamemvvmca.presentation.navigation.HomeBottomBarScreens
import com.krkhvr.gamemvvmca.presentation.ui.theme.Darkgray500
import com.krkhvr.gamemvvmca.presentation.ui.theme.Darkgray700


@Composable
fun HomeScreen(navHostController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            BottomBar(navHostController)
        }
    ) {
        HomeBottomBarNavGraph(navHostController = navHostController, it)
    }
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val screens = listOf(
        HomeBottomBarScreens.Posts,
        HomeBottomBarScreens.MyPosts,
        HomeBottomBarScreens.Profile,
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation(
            backgroundColor = Darkgray500,
            contentColor = Color.White
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navHostController = navHostController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreens,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navHostController.navigate(screen.route) {
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}