package ddd.buyornot.data.prefs

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferenceWrapper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(context.packageName + "." + SharedPreferenceWrapper::class.java.simpleName, Context.MODE_PRIVATE)
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

    var name: String by DelegatedPreferences(prefs, "")
    var birthYear: String by DelegatedPreferences(prefs, "")
    var authenticationCode: String by DelegatedPreferences(prefs, "")

    var nickname: String by DelegatedPreferences(prefs, "")
    var profile: String by DelegatedPreferences(prefs, "")
}
