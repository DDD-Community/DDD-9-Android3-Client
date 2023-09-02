package ddd.buyornot.data.repository.login

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.LoginMethod
import ddd.buyornot.data.model.login.AuthResult
import ddd.buyornot.data.source.login.AuthLocalDataSource
import ddd.buyornot.data.source.login.AuthRemoteDataSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override suspend fun isLoggedIn(): Result<Boolean> {
        return authLocalDataSource.isLoggedIn()
    }

    override suspend fun issueAuthorizationCode(
        accessToken: String,
        loginMethod: LoginMethod
    ): Result<BaseApiResponse<AuthResult>> {
        return authRemoteDataSource.issueAuthorizationCode(accessToken, loginMethod)
            .onSuccess { authInfo ->
                saveAuthorizationCode(
                    authInfo.result?.grantType ?: "Bearer ",
                    authInfo.result?.accessToken ?: "",
                    authInfo.result?.refreshToken ?: ""
                )
            }
    }

    override suspend fun saveAuthorizationCode(
        grantType: String,
        accessToken: String,
        refreshToken: String
    ): Result<Unit> {
        if (accessToken.isEmpty()) {
            return Result.failure(IllegalArgumentException("empty token"))
        }

        return authLocalDataSource.saveAuthorizationCode(grantType, accessToken, refreshToken)
    }

    override suspend fun refreshToken(
        accessToken: String,
        refreshToken: String
    ): Result<BaseApiResponse<AuthResult>> {
        return authRemoteDataSource.refreshToken(accessToken, refreshToken)
            .onSuccess { authInfo ->
                authInfo.result?.let {
                    saveAuthorizationCode(
                        it.grantType ?: "Bearer ",
                        it.accessToken ?: "",
                        it.refreshToken ?: ""
                    )
                }
            }
    }

    override suspend fun logoutRemote(token: String): Result<BaseApiResponse<String>> {
        return authRemoteDataSource.postLogout(token)
    }

    override suspend fun signoutRemote(token: String): Result<BaseApiResponse<String>> {
        return authRemoteDataSource.postSignOut(token)
    }
}