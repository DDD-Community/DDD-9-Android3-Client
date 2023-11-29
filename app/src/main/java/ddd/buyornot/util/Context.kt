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
    startActivity(intent)
}

fun Context.sharePostWeb(postId: Int) {
    val description = "바이올낫 - 어떤 걸 사지? 고민될 때, 바이올낫에서 투표를 올리고 사람들의 의견을 들어보세요! "
    val webDomain = "https://web.buyornot.shop/"

    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, description + webDomain + postId)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, "투표 공유하기")
    startActivity(shareIntent)
}