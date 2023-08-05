package ddd.buyornot.data.prefs

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceWrapper private constructor() {

    companion object {
        val instance = SharedPreferenceWrapper()
    }

    val prefs: SharedPreferences by lazy {
        val context = Application()
        context.getSharedPreferences(context.packageName + "." + SharedPreferenceWrapper::class.java.simpleName, Context.MODE_PRIVATE)
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

    var name: String by DelegatedPreferences(prefs, "")
    var birthYear: String by DelegatedPreferences(prefs, "")
}
