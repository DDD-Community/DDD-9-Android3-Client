package ddd.buyornot.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ddd.component.BDSText
import ddd.buyornot.archive.ArchiveScreen
import ddd.buyornot.home.HomeScreen
import ddd.buyornot.home.viewmodel.HomeViewModel

@Composable
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
fun BuyOrNotNavHost(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = BuyOrNotNavigationRoute.Home.route
    ) {
        composable(BuyOrNotNavigationRoute.Home.route) {
            HomeScreen(homeViewModel)
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