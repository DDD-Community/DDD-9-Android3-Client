package ddd.buyornot.data.util

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class KakaoLogin(private val context: Context, private val postAuth: (String) -> Unit) {
    val TAG = "KakaoLogin"

    fun kakaoLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = this::kakaoSignUp)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    postAuth.invoke(token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = this::kakaoSignUp)
        }
    }

    private fun kakaoSignUp(token: OAuthToken?, error: Throwable?) {
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            UserApiClient.instance.me { user, error ->
                user?.kakaoAccount?.run {
                    val scopes = mutableListOf<String>().apply {
                        when {
                            profileNicknameNeedsAgreement == true -> add("profile_nickname")
                            emailNeedsAgreement == true -> add("account_email")
                            genderNeedsAgreement == true -> add("gender")
                            ageRangeNeedsAgreement == true -> add("age_range")
                        }
                    }

                    if (scopes.isNotEmpty()) {
                        Log.d(TAG, "사용자에게 추가 동의를 받아야 합니다.")

                        // OpenID Connect 사용 시
                        // scope 목록에 "openid" 문자열을 추가하고 요청해야 함
                        // 해당 문자열을 포함하지 않은 경우, ID 토큰이 재발급되지 않음
                        // scopes.add("openid")

                        //scope 목록을 전달하여 카카오 로그인 요청
                        UserApiClient.instance.loginWithNewScopes(context, scopes) { token, error ->
                            if (error != null) {
                                Log.e(TAG, "사용자 추가 동의 실패", error)
                            } else {
                                Log.d(TAG, "allowed scopes: ${token!!.scopes}")

                                // 사용자 정보 재요청
                                UserApiClient.instance.me { user, error ->
                                    if (error != null) {
                                        Log.e(TAG, "사용자 정보 요청 실패", error)
                                    } else if (user != null) {
                                        Log.d(TAG, "유저 닉네임: ${user.kakaoAccount?.profile?.nickname}")
                                        Log.d(TAG, "유저 이메일: ${user.kakaoAccount?.email}")
                                        Log.d(TAG, "유저 성별: ${user.kakaoAccount?.gender?.name}")
                                        Log.d(TAG, "유저 연령대: ${user.kakaoAccount?.ageRange?.name}")
                                        Log.i(TAG, "사용자 정보 요청 성공")
                                    }
                                }
                            }
                        }
                    }
                }
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                postAuth.invoke(token.accessToken)
            }
        }
    }

    fun kakaoLogout() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            }
            else {
                Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
            }
        }
    }
}