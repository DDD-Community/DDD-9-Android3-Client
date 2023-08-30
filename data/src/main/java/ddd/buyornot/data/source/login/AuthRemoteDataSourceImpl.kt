package ddd.buyornot.data.source.login

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.LoginMethod
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

    override suspend fun postLogout(token: String): Result<BaseApiResponse<String>> = runCatching {
        loginService.postLogout(KaKaoAuthRequest(token))
    }

    override suspend fun postSignOut(token: String): Result<BaseApiResponse<String>> = runCatching {
        loginService.postSignOut(KaKaoAuthRequest(token))
    }
}