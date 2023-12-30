package ddd.buyornot

import android.app.Application
import android.widget.Toast
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import ddd.buyornot.data.model.application.Event
import ddd.buyornot.data.repository.login.AuthRepository
import ddd.buyornot.login.LoginActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltAndroidApp
class BuyOrNotApplication : Application() {

    @Inject
    lateinit var authRepository: AuthRepository

    companion object {
        private val _eventSharedFlow = MutableSharedFlow<Event>()

        lateinit var instance: BuyOrNotApplication
            private set

        suspend fun changeEvent(event: Event) {
            _eventSharedFlow.emit(event)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()
        instance = this
        KakaoSdk.init(this, BuildConfig.KAKAO_OAUTH_HOST_SCHEME)
        GlobalScope.launch {
            _eventSharedFlow.collectLatest { event ->
                when (event) {
                    Event.LOGOUT -> {
                        authRepository.logoutRemote()
                            .onSuccess { response ->
                                if (response.isSuccess) {
                                    authRepository.logout()
                                    withContext(Dispatchers.Main) {
                                        LoginActivity.open(baseContext)
                                    }
                                } else {
                                    handleError("로그아웃에 실패했습니다. 다시 시도해주세요.")
                                }
                            }
                            .onFailure {
                                handleError("로그아웃에 실패했습니다. 다시 시도해주세요.")
                            }
                    }
                    Event.SIGN_OUT -> {
                        authRepository.signoutRemote()
                            .onSuccess { response ->
                                if (response.isSuccess) {
                                    authRepository.logout()
                                    withContext(Dispatchers.Main) {
                                        LoginActivity.open(baseContext)
                                    }
                                } else {
                                    handleError("계정 삭제에 실패했습니다. 다시 시도해주세요.")
                                }
                            }
                            .onFailure {
                                handleError("계정 삭제에 실패했습니다. 다시 시도해주세요.")
                            }
                    }
                    else -> {}
                }
            }
        }
    }

    private fun handleError(error: String) {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(baseContext, error, Toast.LENGTH_SHORT).show()
        }
    }
}
