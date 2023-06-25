package ddd.buyornot.login

import android.content.Context
import android.util.Log
import com.kakao.sdk.user.UserApiClient

class KakaoLogin() {
    // 카카오톡으로 로그인
    fun loginWithKakaoTalk(context: Context) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e(TAG, "로그인 실패", error)
                }
                else if (token != null) {
                    Log.i(TAG, "로그인 성공 ${token.accessToken}")
                }
            }
        }
    }

    // 카카오 계정으로 로그인
    fun loginWithKakaoAccount(context: Context) {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)
            }
            else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
            }
        }
    }



    companion object {
        const val TAG = "KAKAO_LOGIN"
    }
}