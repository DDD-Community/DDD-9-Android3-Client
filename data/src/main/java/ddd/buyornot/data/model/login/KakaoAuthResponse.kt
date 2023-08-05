package ddd.buyornot.data.model.login

import com.google.gson.annotations.SerializedName

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