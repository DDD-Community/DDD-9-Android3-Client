package ddd.buyornot

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ddd.component.theme.BuyOrNotTheme

class SubActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BuyOrNotTheme {
            }
        }
    }
}

fun Intent?.getShareString(default: String): String {
    return when (this?.action) {
        Intent.ACTION_SEND -> {
            this.getStringExtra(Intent.EXTRA_TEXT) ?: default
        }

        else -> {
            default
        }
    }
}