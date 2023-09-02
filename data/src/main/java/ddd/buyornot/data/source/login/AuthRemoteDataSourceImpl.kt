package ddd.buyornot.data.source.login

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.LoginMethod
import ddd.buyornot.data.model.login.AuthRefreshRequest
import ddd.buyornot.data.model.login.AuthResult
import ddd.buyornot.data.model.login.KaKaoAuthRequest
import ddd.buyornot.data.service.LoginService
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val loginService: LoginService
) : AuthRemoteDataSource {

    override suspend fun issueAuthorizationCode(
        token: String,
        loginMethod: LoginMethod
    ) = runCatching {
        loginService.postKakaoAuth(KaKaoAuthRequest(token))
    }

    override suspend fun logout(): Result<Boolean> = runCatching {
        loginService.logout().isSuccess
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