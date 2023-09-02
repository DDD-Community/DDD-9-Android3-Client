package ddd.buyornot.data.source.login

import ddd.buyornot.data.prefs.SharedPreferenceWrapper
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val prefWrapper: SharedPreferenceWrapper
) : AuthLocalDataSource {

    override suspend fun isLoggedIn(): Result<Boolean> {
        return runCatching {
            prefWrapper.authenticationCode.isNotBlank()
        }
    }

    override suspend fun saveAuthorizationCode(code: String, refreshToken: String?): Result<Unit> {
        return runCatching {
            prefWrapper.authenticationCode = code
            prefWrapper.refreshToken = refreshToken ?: ""
        }
    }

    override suspend fun clearLocalData(): Result<Unit> {
        return kotlin.runCatching {
            prefWrapper.clear()
        }
    }
}