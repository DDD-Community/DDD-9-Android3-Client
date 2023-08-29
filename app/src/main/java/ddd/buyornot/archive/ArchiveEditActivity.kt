package ddd.buyornot.archive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BuyOrNotTheme
import dagger.hilt.android.AndroidEntryPoint
import ddd.buyornot.archive.viewmodel.ArchiveViewModel

@ExperimentalMaterial3Api
@AndroidEntryPoint
class ArchiveEditActivity : ComponentActivity() {

    private val archiveViewModel by viewModels<ArchiveViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BuyOrNotTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = BDSColor.White)
                ) {
                    ArchiveEditScreen(archiveViewModel)
                }
            }
        }
    }

}