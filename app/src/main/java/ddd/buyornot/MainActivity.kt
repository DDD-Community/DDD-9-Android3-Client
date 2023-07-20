package ddd.buyornot

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.ddd.component.BDSBottomNavigationLayout
import com.ddd.component.BottomNavigationItem
import com.ddd.component.theme.BuyOrNotTheme
import ddd.buyornot.add_vote.AddNewVoteActivity
import ddd.buyornot.navigation.BuyOrNotNavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

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
                        handleNavigationEvent(it)
                    }
                ) {
                    BuyOrNotNavHost(navHostController = navHostController)
                }
            }
        }
    }

    private fun handleNavigationEvent(bottomNavigationItem: BottomNavigationItem) {
        when (bottomNavigationItem) {
            is BottomNavigationItem.Add -> {
                startActivity(
                    Intent(this, AddNewVoteActivity::class.java)
                )
            }

            else -> {

            }
        }
    }
}
