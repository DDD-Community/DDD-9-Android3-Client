package ddd.buyornot.data.source.login

import ddd.buyornot.data.prefs.SharedPreferenceWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val prefWrapper: SharedPreferenceWrapper
) : AuthLocalDataSource {

    override fun isLoggedIn() = flow { emit(prefWrapper.accessToken.isNotBlank()) }

    override suspend fun saveAuthorizationCode(
        grantType: String,
        accessToken: String,
        refreshToken: String
    ): Result<Unit> {
        return runCatching {
            prefWrapper.grantType = grantType
            prefWrapper.accessToken = accessToken
            prefWrapper.refreshToken = refreshToken
        }
    }

    override suspend fun clearLocalData(): Result<Unit> {
        return kotlin.runCatching {
            prefWrapper.clear()
            isLoggedIn()
        }
    }
}