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

    override suspend fun issueAuthorizationCode(token: String, loginMethod: LoginMethod): Result<BaseApiResponse<AuthResult>> {
        return authRemoteDataSource.issueAuthorizationCode(token, loginMethod).onSuccess { authInfo ->
            val authorizationHeader = authInfo.result?.run { grantType + accessToken } ?: ""
            saveAuthorizationCode(authorizationHeader)
        }
    }

    override suspend fun saveAuthorizationCode(code: String): Result<Unit> {
        if (code.isEmpty()) {
            return Result.failure(IllegalArgumentException("empty token"))
        }

        return authLocalDataSource.saveAuthorizationCode(code)
    }

    override suspend fun logout(): Result<Unit> {
        return authLocalDataSource.clearLocalData()
    }

    override suspend fun logoutRemote(token: String): Result<BaseApiResponse<String>> {
        return authRemoteDataSource.postLogout(token)
    }

    override suspend fun signoutRemote(token: String): Result<BaseApiResponse<String>> {
        return authRemoteDataSource.postSignOut(token)
    }
}