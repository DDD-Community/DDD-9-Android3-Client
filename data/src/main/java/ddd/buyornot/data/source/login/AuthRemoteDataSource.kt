package ddd.buyornot.data.source.login

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.LoginMethod
import ddd.buyornot.data.model.login.AuthResult

interface AuthRemoteDataSource {

    suspend fun issueAuthorizationCode(
        token: String,
        loginMethod: LoginMethod = LoginMethod.KAKAO
    ): Result<BaseApiResponse<AuthResult>>

    suspend fun refreshToken(
        accessToken: String,
        refreshToken: String
    ): Result<BaseApiResponse<AuthResult>>

    suspend fun postLogout(): Result<BaseApiResponse<String>>

    suspend fun postSignOut(): Result<BaseApiResponse<String>>
}