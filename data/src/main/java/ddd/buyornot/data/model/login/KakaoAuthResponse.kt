package ddd.buyornot.data.model.login

import com.google.gson.annotations.SerializedName

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