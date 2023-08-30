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

    suspend fun saveAuthorizationCode(code: String): Result<Unit>

    suspend fun logout(): Result<Unit>

    suspend fun logoutRemote(token: String): Result<BaseApiResponse<String>>
}