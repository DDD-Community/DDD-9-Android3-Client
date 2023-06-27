package ddd.buyornot

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ddd.component.theme.BuyOrNotTheme

class SubActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BuyOrNotTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                }
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