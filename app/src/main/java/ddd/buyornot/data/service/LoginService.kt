package ddd.buyornot.data.service

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/auth/kakao")
    suspend fun postKakaoAuth(
        @Body kakaoAuthRequest: KaKaoAuthRequest
    ) : KakaoAuthResponse

    data class KaKaoAuthRequest(
        val authorizationCode: String
    )

    data class KakaoAuthResponse(
        @SerializedName("result")
        val result: AuthResult?,

        @SerializedName("resultCode")
        val resultCode: Int = 0,

        @SerializedName("resultMsg")
        val resultMsg: String?

    ) {
         data class AuthResult(
             @SerializedName("accessToken")
             val accessToken: String?,

             @SerializedName("refreshToken")
             val refreshToken: String?,

             @SerializedName("grantType")
             val grantType: String?,

             @SerializedName("expiresIn")
             val expiresIn: Int = 0
         )
    }
}
