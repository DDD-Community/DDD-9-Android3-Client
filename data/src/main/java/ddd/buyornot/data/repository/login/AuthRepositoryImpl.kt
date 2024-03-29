package ddd.buyornot.data.repository.login

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.LoginMethod
import ddd.buyornot.data.model.login.AuthResult
import ddd.buyornot.data.source.login.AuthLocalDataSource
import ddd.buyornot.data.source.login.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override fun isLoggedIn(): Flow<Boolean> {
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
            }.onFailure {
                logout()
            }
    }

    override suspend fun logout(): Result<Unit> {
        return authLocalDataSource.clearLocalData()
    }

    override suspend fun logoutRemote(): Result<BaseApiResponse<String>> {
        return authRemoteDataSource.postLogout()
    }

    override suspend fun signoutRemote(): Result<BaseApiResponse<String>> {
        return authRemoteDataSource.postSignOut()
    }
}