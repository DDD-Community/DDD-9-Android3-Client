package ddd.buyornot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier
import com.ddd.component.BDSImage
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ddd.component.BDSBottomNavigation
import com.ddd.component.theme.BuyOrNotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            BuyOrNotTheme {
                IconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { }
                ) {
                    BDSImage(
                        modifier = Modifier.fillMaxWidth(),
                        resId = R.drawable.kakao_login_medium_wide                    )
                }
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
