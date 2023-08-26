package ddd.buyornot.upload_item.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ddd.buyornot.upload_item.viewmodel.UploadItemViewModel

@AndroidEntryPoint
class UploadItemActivity : ComponentActivity() {

    private val viewModel: UploadItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UploadItemScreen(viewModel = viewModel)
        }
    }
}
