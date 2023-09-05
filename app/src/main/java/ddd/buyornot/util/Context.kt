package ddd.buyornot.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

fun Context.openWeb(itemUrl: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(itemUrl))
    this.findActivity().startActivity(intent)
}