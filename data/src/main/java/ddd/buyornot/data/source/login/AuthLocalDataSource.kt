package ddd.buyornot.data.source.login

import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {

    fun isLoggedIn(): Flow<Boolean>

    suspend fun saveAuthorizationCode(
        grantType: String,
        accessToken: String,
        refreshToken: String
    ): Result<Unit>

    suspend fun clearLocalData(): Result<Unit>
}