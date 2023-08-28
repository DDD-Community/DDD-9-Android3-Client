package ddd.buyornot.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ddd.buyornot.archive.ArchiveScreen
import ddd.buyornot.archive.viewmodel.ArchiveViewModel
import ddd.buyornot.home.HomeScreen

@Composable
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
fun BuyOrNotNavHost(
    navHostController: NavHostController,
    archiveViewModel: ArchiveViewModel
) {

    NavHost(
        navController = navHostController,
        startDestination = BuyOrNotNavigationRoute.Home.route
    ) {
        composable(BuyOrNotNavigationRoute.Home.route) {
            HomeScreen()
        }
        composable(BuyOrNotNavigationRoute.Archive.route) {
            ArchiveScreen(archiveViewModel)
        }
    }
}

sealed interface BuyOrNotNavigationRoute {

    val route: String
        get() = this::class.java.name

    object Home : BuyOrNotNavigationRoute

    object Archive : BuyOrNotNavigationRoute
}