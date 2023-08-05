package ddd.buyornot.data.service

import ddd.buyornot.data.model.login.KaKaoAuthRequest
import ddd.buyornot.data.model.login.KakaoAuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/auth/kakao")
    suspend fun postKakaoAuth(
        @Body kakaoAuthRequest: KaKaoAuthRequest
    ): KakaoAuthResponse
}
