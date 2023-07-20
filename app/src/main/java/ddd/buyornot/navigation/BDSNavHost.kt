package ddd.buyornot.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ddd.buyornot.archive.ArchiveScreen
import ddd.buyornot.home.HomeScreen

@Composable
fun BuyOrNotNavHost(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = BuyOrNotNavigationRoute.Home.route
    ) {
        composable(BuyOrNotNavigationRoute.Home.route) {
            HomeScreen()
        }
        composable(BuyOrNotNavigationRoute.Archive.route) {
            ArchiveScreen()
        }
    }
}

sealed interface BuyOrNotNavigationRoute {

    val route: String
        get() = this::class.java.name

    object Home : BuyOrNotNavigationRoute

    object Archive : BuyOrNotNavigationRoute
}