package ddd.buyornot.upload_item.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSAppBar
import com.ddd.component.BDSButton
import com.ddd.component.BDSTab
import com.ddd.component.BDSText
import com.ddd.component.BDSTextField
import com.ddd.component.BDSTextFieldState
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BuyOrNotTheme
import ddd.buyornot.findActivity
import ddd.buyornot.upload_item.viewmodel.UploadItemViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun UploadItemScreen(
    viewModel: UploadItemViewModel
) {
    BuyOrNotTheme {
        val activity = LocalContext.current.findActivity()
        val scrollState = rememberScrollState()
        var bdsTextFieldState: BDSTextFieldState by remember { mutableStateOf(BDSTextFieldState.UnFocus) }
        var tabIndex by remember { mutableStateOf(0) }

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
                                    activity.finish()
                                }
                                .padding(start = 24.dp),
                            fontSize = 16.sp,
                            color = BDSColor.Gray500,
                        )
                    },
                    title = "상품 등록",
                )

                BDSTab(
                    titles = listOf("링크 붙여넣기", "내 아카이브함"),
                    selectedTabIndex = tabIndex,
                    onTabSelected = {
                        tabIndex = it
                    }
                )
                when (tabIndex) {
                    0 -> {
                        PasteItemUrlScreen(
                            onUrlChanged = { url ->
                                viewModel.onUrlChanged(url)
                            },
                            onClickButton = { url ->
                                viewModel.checkUrl(url)
                            }
                        )
                    }

                    else -> {

                    }
                }
            }

            BDSButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 36.dp)
                    .align(alignment = Alignment.BottomCenter),
                text = "상품 등록하기",
                contentPadding = PaddingValues(13.dp),
            ) {

            }
        }
    }
}

@Composable
private fun PasteItemUrlScreen(
    onUrlChanged: (String) -> Unit,
    onClickButton: (String) -> Unit,
) {
    val clipboardManager = LocalClipboardManager.current
    var state: BDSTextFieldState by remember { mutableStateOf(BDSTextFieldState.UnFocus) }
    var itemUrl by remember { mutableStateOf("") }
    var clipboardContent by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val clipboardText = clipboardManager.getText()?.trim().toString()
        if (clipboardText.isNotEmpty()) {
            clipboardContent = clipboardText
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 36.dp)
    ) {
        BDSTextField(
            value = itemUrl,
            onValueChange = { value ->
                itemUrl = value
                onUrlChanged(value)
            },
            onFocusChanged = { focusState ->
                state =
                    if (focusState.isFocused) BDSTextFieldState.Focus else BDSTextFieldState.UnFocus
            },
            title = "상품 링크 등록하기",
            hint = "투표에 올릴 상품 링크를 등록해주세요",
            state = state,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            maxLine = Int.MAX_VALUE,
        )

        BDSButton(
            modifier = Modifier.padding(top = 2.dp),
            text = when {
                itemUrl.isEmpty() && clipboardContent.startsWith("http") -> {
                    clipboardContent.take(30) + "... 붙여넣기"
                }

                else -> "상품 확인하기"
            },
            fontSize = 14.sp,
            contentColor = BDSColor.SlateGray800,
            containerColor = BDSColor.SlateGray300,
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 7.dp),
        ) {
            if (clipboardContent.isNotEmpty()) {
                itemUrl = clipboardContent
                onUrlChanged(clipboardContent)
                clipboardContent = ""
            } else {
                onClickButton(itemUrl)
            }
        }
    }

}