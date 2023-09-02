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
        token: String,
        loginMethod: LoginMethod
    ): Result<BaseApiResponse<AuthResult>> {
        return authRemoteDataSource.issueAuthorizationCode(token, loginMethod)
            .onSuccess { authInfo ->
                val authorizationHeader = authInfo.result?.run { grantType + accessToken } ?: ""
                saveAuthorizationCode(authorizationHeader, authInfo.result?.refreshToken)
            }
    }

    override suspend fun saveAuthorizationCode(code: String, refreshToken: String?): Result<Unit> {
        if (code.isEmpty()) {
            return Result.failure(IllegalArgumentException("empty token"))
        }

        return authLocalDataSource.saveAuthorizationCode(code, refreshToken)
    }

    override suspend fun logout(): Result<Boolean> {
        return authRemoteDataSource.logout()
            .onSuccess {
                authLocalDataSource.clearLocalData()
                Result.success(Unit)
            }.onFailure {
                Result.failure<Boolean>(it)
            }
    }

    override suspend fun refreshToken(refreshToken: String): Result<BaseApiResponse<AuthResult>> {
        return authRemoteDataSource.refreshToken(refreshToken)
            .onSuccess { authInfo ->
                val authorizationHeader = authInfo.result?.run { grantType + accessToken } ?: ""
                saveAuthorizationCode(authorizationHeader, authInfo.result?.refreshToken)
            }
    }
}