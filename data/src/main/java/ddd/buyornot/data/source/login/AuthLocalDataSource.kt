package ddd.buyornot.data.source.login

interface AuthLocalDataSource {

    suspend fun isLoggedIn(): Result<Boolean>

    suspend fun saveAuthorizationCode(
        grantType: String,
        accessToken: String,
        refreshToken: String
    ): Result<Unit>

    suspend fun clearLocalData(): Result<Unit>
}