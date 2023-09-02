package ddd.buyornot.data.repository.login

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.LoginMethod
import ddd.buyornot.data.model.login.AuthResult

interface AuthRepository {

    suspend fun isLoggedIn(): Result<Boolean>

    suspend fun issueAuthorizationCode(
        token: String,
        loginMethod: LoginMethod = LoginMethod.KAKAO
    ): Result<BaseApiResponse<AuthResult>>

    suspend fun refreshToken(
        refreshToken: String
    ): Result<BaseApiResponse<AuthResult>>

    suspend fun logout(): Result<Boolean>

    suspend fun saveAuthorizationCode(code: String, refreshToken: String?): Result<Unit>
}