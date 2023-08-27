package ddd.buyornot

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ddd.component.BDSBottomNavigationLayout
import com.ddd.component.BottomNavigationItem
import com.ddd.component.theme.BuyOrNotTheme
import dagger.hilt.android.AndroidEntryPoint
import ddd.buyornot.add_vote.ui.AddNewVoteActivity
import ddd.buyornot.data.repository.login.AuthRepository
import ddd.buyornot.home.viewmodel.HomeViewModel
import ddd.buyornot.login.LoginActivity
import ddd.buyornot.navigation.BuyOrNotNavHost
import ddd.buyornot.navigation.BuyOrNotNavigationRoute
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: AuthRepository

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repository.isLoggedIn().onSuccess {
                if (!it) {
                    startKakaoLogin()
                }
            }.onFailure {
                startKakaoLogin()
            }
        }

        setContent {
            BuyOrNotTheme {
                val navHostController = rememberNavController()
                var selectedBottomNavigation: BottomNavigationItem by remember {
                    mutableStateOf(BottomNavigationItem.bottomNavigationItems.first())
                }

                BDSBottomNavigationLayout(
                    selectedNavigationItem = selectedBottomNavigation,
                    onClickNavigationItem = {
                        selectedBottomNavigation = it
                        handleNavigationEvent(navHostController, it)
                    }
                ) {
                    BackHandler {
                        onBackPressed(navHostController)
                    }
                    BuyOrNotNavHost(
                        navHostController = navHostController,
                        homeViewModel = homeViewModel
                    )
                }
            }
        }
    }

    private fun startKakaoLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun onBackPressed(navHostController: NavHostController) {
        val currentRoute = navHostController.currentBackStackEntry?.destination?.route
        if (currentRoute in listOf(BuyOrNotNavigationRoute.Home.route, BuyOrNotNavigationRoute.Archive.route)) {
            finish()
        } else {
            navHostController.popBackStack()
        }
    }

    private fun handleNavigationEvent(navHostController: NavHostController, bottomNavigationItem: BottomNavigationItem) {
        when (bottomNavigationItem) {
            is BottomNavigationItem.Home -> {
                navHostController.navigate(BuyOrNotNavigationRoute.Home.route) {
                    launchSingleTop = true
                    popUpTo(BuyOrNotNavigationRoute.Archive.route) { inclusive = true }
                }
            }

            is BottomNavigationItem.Add -> {
                startActivity(
                    Intent(this, AddNewVoteActivity::class.java)
                )
            }

            is BottomNavigationItem.Archive -> {
                navHostController.navigate(BuyOrNotNavigationRoute.Archive.route) {
                    launchSingleTop = true
                    popUpTo(BuyOrNotNavigationRoute.Home.route) { inclusive = true }
                }
            }
        }
    }
}
