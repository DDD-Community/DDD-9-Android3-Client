package com.ddd.component.demo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
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
        composable(DemoNavigationRoute.Theme.route) {
            ThemeTestScreen()
        }
        composable(DemoNavigationRoute.PostCard.route) {
            PostCardTestScreen()
        }
        composable(DemoNavigationRoute.Button.route) {
            ButtonTestScreen()
        }
        composable(DemoNavigationRoute.TextField.route) {
            TextFieldScreen()
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

    object ItemCard : DemoNavigationRoute {
        override val route: String = "itemCard"
    }
    object PostCard : DemoNavigationRoute {
        override val route: String = "postCard"
    }
    object ArchiveScreen : DemoNavigationRoute {
        override val route: String = "archiveScreen"
    }
    object ArchiveEditScreen : DemoNavigationRoute {
        override val route: String = "archiveEditScreen"
    }
    object ProfileScreen : DemoNavigationRoute {
        override val route: String = "profileScreen"
    }
    object Button : DemoNavigationRoute {
        override val route: String = "button"
    }
    object TextField : DemoNavigationRoute {
        override val route: String = "textField"
    }
}