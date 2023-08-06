package ddd.buyornot.data.util

import android.content.Context
import android.util.Log
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import ddd.buyornot.data.BuildConfig
import ddd.buyornot.data.prefs.SharedPreferenceWrapper

/*
    네이버 로그인 사용법

    네이버 로그인 시 initialize, authenticate 함수를 순차적으로 실행하면 된다.
    context는 로그인을 시도하는 activity를 넘겨주도록 한다.

    추후에 로그인 화면의 ViewModel로 migration 예정
 */
class NaverLogin(
    sharedPreferenceWrapper: SharedPreferenceWrapper
) {

    private val oAuthLoginCallback = object : OAuthLoginCallback {
        override fun onSuccess() {
            NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                override fun onSuccess(result: NidProfileResponse) {
                    // result로 유저 정보 확인 가능
                    val name = result.profile?.name.toString()
                    val birthYear = result.profile?.birthYear.toString()

                    sharedPreferenceWrapper.name = name
                    sharedPreferenceWrapper.birthYear = birthYear

                    Log.d("Naver Login", "로그인 유저 이름 : $name")
                    Log.d("Naver Login", "로그인 유저 출생연도 : $birthYear")
                }

                override fun onError(errorCode: Int, message: String) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(httpStatus: Int, message: String) {
                    TODO("Not yet implemented")
                }
            })
        }

        override fun onError(errorCode: Int, message: String) {
            TODO("Not yet implemented")
        }

        override fun onFailure(httpStatus: Int, message: String) {
            TODO("Not yet implemented")
        }
    }

    fun initialize(context: Context) {
        NaverIdLoginSDK.initialize(context, BuildConfig.NAVER_LOGIN_ID, BuildConfig.NAVER_LOGIN_SECRET, "BuyOrNot")
    }

    fun authenticate(context: Context) {
        NaverIdLoginSDK.authenticate(context, oAuthLoginCallback)
    }
}