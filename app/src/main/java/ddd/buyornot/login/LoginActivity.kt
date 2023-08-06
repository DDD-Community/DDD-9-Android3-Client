package ddd.buyornot.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier
import com.ddd.component.BDSImage
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BuyOrNotTheme
import dagger.hilt.android.AndroidEntryPoint
import ddd.buyornot.R
import ddd.buyornot.data.model.login.KaKaoAuthRequest
import ddd.buyornot.data.service.LoginService
import ddd.buyornot.data.util.KakaoLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    @Inject
    lateinit var loginService: LoginService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BuyOrNotTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = BDSColor.Background300),
                ) {
                    val kakaoLogin = KakaoLogin(baseContext) { token ->
                        CoroutineScope(Dispatchers.IO).launch {
                            loginService.postKakaoAuth(KaKaoAuthRequest(token))
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
}