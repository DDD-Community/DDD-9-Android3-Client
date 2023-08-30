package ddd.buyornot.data.service

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.login.AuthResult
import ddd.buyornot.data.model.login.KaKaoAuthRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/auth/kakao")
    suspend fun postKakaoAuth(
        @Body kakaoAuthRequest: KaKaoAuthRequest
    ): BaseApiResponse<AuthResult>

    @POST("/auth/logout")
    suspend fun postLogout(): BaseApiResponse<String>

}
