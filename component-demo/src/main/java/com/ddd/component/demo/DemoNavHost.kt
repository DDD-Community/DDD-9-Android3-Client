package com.ddd.component.demo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun DemoNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = DemoNavigationRoute.Home.route
    ) {
        composable(DemoNavigationRoute.Home.route) {
            DemoHomeScreen(navController)
        }
        composable(DemoNavigationRoute.Typography.route) {
            TypographyTestScreen()
        }
        composable(DemoNavigationRoute.Image.route) {
            ImageTestScreen()
        }
    }
}

internal sealed interface DemoNavigationRoute {

    abstract val route: String

    object Home : DemoNavigationRoute {
        override val route: String = "home"
    }

    object Typography : DemoNavigationRoute {
        override val route: String = "typography"
    }

    object Image : DemoNavigationRoute {
        override val route: String = "image"
    }
}