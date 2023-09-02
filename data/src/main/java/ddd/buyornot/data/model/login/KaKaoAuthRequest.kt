package ddd.buyornot.data.model.login

data class KaKaoAuthRequest(
    val accessToken: String
)

data class AuthRefreshRequest(
    val accessToken: String,
    val refreshToken: String
)