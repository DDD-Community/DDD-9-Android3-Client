package ddd.buyornot.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.ddd.component.BDSButton
import com.ddd.component.BDSImage
import com.ddd.component.theme.BuyOrNotTheme
import dagger.hilt.android.AndroidEntryPoint
import ddd.buyornot.MainActivity
import ddd.buyornot.R
import ddd.buyornot.data.prefs.SharedPreferenceWrapper
import ddd.buyornot.data.repository.login.AuthRepository
import ddd.buyornot.data.repository.user.UserRepository
import ddd.buyornot.data.util.KakaoLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var sharedPreferenceWrapper: SharedPreferenceWrapper

    // val pref = SharedPreferenceWrapper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val isLoggedIn = authRepository.isLoggedIn().getOrNull() ?: false
            if (isLoggedIn) {
                startMainActivityAndFinish()
            }
        }

        val kakaoLogin = KakaoLogin(this@LoginActivity) { token ->
            CoroutineScope(Dispatchers.IO).launch {
                authRepository.issueAuthorizationCode(token)
                    .onSuccess { response ->
                        if (response.isSuccess) {
                            lifecycleScope.launch {
                                val result = userRepository.fetchProfile()?.result?.let {
                                    sharedPreferenceWrapper.nickname = it.nickname ?: ""
                                    sharedPreferenceWrapper.profile = it.profile ?:""
                                }
                            }
                            startMainActivityAndFinish()
                        } else {
                            handleLoginError()
                        }
                    }.onFailure {
                        handleLoginError()
                    }
            }
        }

        setContent {
            BuyOrNotTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    BDSImage(
                        modifier = Modifier
                            .size(248.dp, 47.dp),
                        resId = R.drawable.ic_text_logo,
                        contentScale = ContentScale.Fit,
                    )
                    Spacer(modifier = Modifier.height(19.dp))
                    BDSImage(
                        modifier = Modifier
                            .size(307.dp, 272.dp),
                        resId = R.drawable.graphic_onboarding,
                        contentScale = ContentScale.Fit,
                    )
                    BDSButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 144.dp),
                        text = "카카오톡으로 계속하기",
                        onClick = {
                            kakaoLogin.kakaoLogin()
                        },
                        contentPadding = PaddingValues(16.dp)
                    )
                }
            }
        }
    }

    private fun startMainActivityAndFinish() {
        runOnUiThread {
            Toast.makeText(this, "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }

    private fun handleLoginError() {
        lifecycleScope.launch(Dispatchers.Main) {
            Toast.makeText(this@LoginActivity, "로그인에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}