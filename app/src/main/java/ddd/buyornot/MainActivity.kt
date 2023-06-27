package ddd.buyornot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ddd.component.BDSBottomNavigation
import com.ddd.component.theme.BuyOrNotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            BuyOrNotTheme {
                BDSBottomNavigation(
                    onClickTab = {
                        // todo
                    },
                    content = {
                        // todo
                    }
                )
            }
        }
    }
}
