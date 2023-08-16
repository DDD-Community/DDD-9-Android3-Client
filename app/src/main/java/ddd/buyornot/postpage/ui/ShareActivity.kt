package ddd.buyornot.postpage.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.ddd.component.theme.BuyOrNotTheme
import dagger.hilt.android.AndroidEntryPoint
import ddd.buyornot.postpage.ui.bottomsheet.PostPageNavHost
import ddd.buyornot.postpage.viewmodel.ShareViewModel

// TODO: post 네비게이션 구현
@ExperimentalMaterial3Api
@AndroidEntryPoint
class ShareActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BuyOrNotTheme {
                val navHostController = rememberNavController()

                PostPageNavHost(
                    navHostController = navHostController,
                )
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