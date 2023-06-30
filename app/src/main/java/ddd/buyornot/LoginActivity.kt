package ddd.buyornot

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier
import com.ddd.component.BDSImage
import com.ddd.component.theme.BuyOrNotTheme
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import ddd.buyornot.data.service.LoginService
import ddd.buyornot.login.KakaoLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    @Inject
    lateinit var loginService: LoginService

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val TAG = "KakaoLogin"

        setContent {
            BuyOrNotTheme {
                val kakaoLogin = KakaoLogin(baseContext) { token ->
                    CoroutineScope(Dispatchers.IO).launch {
                        loginService.postKakaoAuth(LoginService.KaKaoAuthRequest(token))
                    }
                }

                IconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        kakaoLogin.kakaoLogin()
                    }
                ) {
                    BDSImage(
                        modifier = Modifier.fillMaxWidth(),
                        resId = R.drawable.kakao_login_medium_wide
                    )
                }
            }
        }
    }
}