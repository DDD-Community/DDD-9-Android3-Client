package ddd.buyornot.data.source.login

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.LoginMethod
import ddd.buyornot.data.model.login.AuthRefreshRequest
import ddd.buyornot.data.model.login.AuthResult
import ddd.buyornot.data.model.login.KaKaoAuthRequest
import ddd.buyornot.data.service.LoginService
import ddd.buyornot.data.service.LogoutService
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val loginService: LoginService,
    private val logoutService: LogoutService
) : AuthRemoteDataSource {

    override suspend fun issueAuthorizationCode(
        token: String,
        loginMethod: LoginMethod
    ) = runCatching {
        loginService.postKakaoAuth(KaKaoAuthRequest(token))
    }

    override suspend fun postLogout(): Result<BaseApiResponse<String>> = runCatching {
        logoutService.postLogout()
    }

    override suspend fun postSignOut(): Result<BaseApiResponse<String>> = runCatching {
        logoutService.postSignOut()
    }

    override suspend fun refreshToken(
        accessToken: String,
        refreshToken: String
    ): Result<BaseApiResponse<AuthResult>> {
        return runCatching {
            loginService.refresh(AuthRefreshRequest(accessToken, refreshToken))
        }
    }
}