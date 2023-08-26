package ddd.buyornot.upload_item.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSAppBar
import com.ddd.component.BDSText
import com.ddd.component.BDSTextFieldState
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BuyOrNotTheme
import ddd.buyornot.upload_item.viewmodel.UploadItemViewModel

@Composable
fun UploadItemScreen(
    viewModel: UploadItemViewModel
) {
    BuyOrNotTheme {
        val activity = LocalContext.current as? Activity
        val scrollState = rememberScrollState()
        var bdsTextFieldState: BDSTextFieldState by remember { mutableStateOf(BDSTextFieldState.UnFocus) }

        var isTempStorageAvailable by remember { mutableStateOf(false) }
        var voteTitle by remember { mutableStateOf("") }
        var voteDescription by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(color = BDSColor.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                BDSAppBar(
                    modifier = Modifier
                        .height(54.dp)
                        .fillMaxWidth(),
                    left = {
                        BDSText(
                            text = "취소",
                            modifier = Modifier
                                .clickable {
                                    activity?.finish()
                                }
                                .padding(start = 24.dp),
                            fontSize = 16.sp,
                            color = BDSColor.Gray500,
                        )
                    },
                    right = {
                        BDSText(
                            text = "임시저장",
                            fontSize = 16.sp,
                            color = if (isTempStorageAvailable) BDSColor.Gray500 else BDSColor.Gray300,
                            modifier = Modifier
                                .padding(end = 22.dp)
                                .clickable {

                                }
                        )
                    },
                    title = "투표글 쓰기",
                )

            }
        }
    }
}