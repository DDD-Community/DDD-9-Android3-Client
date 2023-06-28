package ddd.buyornot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.ddd.component.BDSImage
import com.ddd.component.theme.BuyOrNotTheme
import ddd.buyornot.login.KakaoLogin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyOrNotTheme {
                val kakaoLogin = KakaoLogin(baseContext)
                IconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { kakaoLogin.kakaoLogin() }
                ) {
                    BDSImage(
                        modifier = Modifier.fillMaxWidth(),
                        resId = R.drawable.kakao_login_medium_wide                    )
                }
            }
        }
    }
}
