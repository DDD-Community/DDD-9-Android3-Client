package ddd.buyornot.data.repository.login

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.LoginMethod
import ddd.buyornot.data.model.login.AuthResult

interface AuthRepository {

    suspend fun isLoggedIn(): Result<Boolean>

    suspend fun issueAuthorizationCode(
        accessToken: String,
        loginMethod: LoginMethod = LoginMethod.KAKAO
    ): Result<BaseApiResponse<AuthResult>>

    suspend fun refreshToken(
        accessToken: String,
        refreshToken: String
    ): Result<BaseApiResponse<AuthResult>>

    suspend fun logoutRemote(token: String): Result<BaseApiResponse<String>>

    suspend fun signoutRemote(token: String): Result<BaseApiResponse<String>>

    suspend fun saveAuthorizationCode(
        grantType: String,
        accessToken: String,
        refreshToken: String
    ): Result<Unit>
}