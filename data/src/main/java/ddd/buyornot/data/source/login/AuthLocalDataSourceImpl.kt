package ddd.buyornot.data.source.login

import android.util.Log
import ddd.buyornot.data.prefs.SharedPreferenceWrapper
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val prefWrapper: SharedPreferenceWrapper
) : AuthLocalDataSource {

    override suspend fun isLoggedIn(): Result<Boolean> {
        return runCatching {
            Log.d("", "isLoggedIn: ${prefWrapper.authenticationCode}")
            prefWrapper.authenticationCode.isNotBlank()
        }
    }

    override suspend fun saveAuthorizationCode(code: String): Result<Unit> {
        return runCatching {
            prefWrapper.authenticationCode = code
        }
    }

    override suspend fun clearLocalData(): Result<Unit> {
        return kotlin.runCatching {
            prefWrapper.clear()
        }
    }
}