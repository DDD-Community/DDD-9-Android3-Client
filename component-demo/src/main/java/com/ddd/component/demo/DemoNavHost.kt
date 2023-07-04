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
        composable(DemoNavigationRoute.Layout.route) {
            LayoutTestScreen()
        }
        composable(DemoNavigationRoute.Layout.route) {
            ThemeTestScreen()
        }
    }
}

internal sealed interface DemoNavigationRoute {

    val route: String

    object Home : DemoNavigationRoute {
        override val route: String = "home"
    }

    object Typography : DemoNavigationRoute {
        override val route: String = "typography"
    }

    object Image : DemoNavigationRoute {
        override val route: String = "image"
    }

    object Layout : DemoNavigationRoute {
        override val route: String = "layout"
    }

    object Theme : DemoNavigationRoute {
        override val route: String = "theme"
    }
}