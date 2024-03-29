package ddd.buyornot.postpage.ui.bottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSBorderlessButton
import com.ddd.component.BDSBottomSheet
import com.ddd.component.BDSBottomSheetHeader
import com.ddd.component.BDSBottomSheetVerticalDualButton
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSSwitch
import com.ddd.component.BDSText
import com.ddd.component.BDSTextField
import com.ddd.component.BDSTextFieldState
import com.ddd.component.theme.BDSColor
import ddd.buyornot.postpage.viewmodel.ShareViewModel
import kotlinx.coroutines.launch

@Composable
fun PostPageContentBottomSheet(
    title: String,
    viewModel: ShareViewModel,
    onDismissRequest: () -> Unit = { },
    onClickNext: () -> Unit = { }
) {
    val coroutineScope = rememberCoroutineScope()

    val savedContent = viewModel.selectedPost.value?.content ?: ""
    var content by remember {
        mutableStateOf(savedContent)
    }
    var state: BDSTextFieldState by remember { mutableStateOf(BDSTextFieldState.UnFocus) }
    var checked: Boolean by remember { mutableStateOf(false) }

    BDSBottomSheet(
        onDismissRequest = onDismissRequest,
        limitHeight = false,
        headerContent = {
            BDSBottomSheetHeader(
                center = {
                    BDSText(
                        text = title,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BDSColor.SlateGray900
                    )
                }
            )
        },
        bodyContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .height(155.dp)
                ) {
                    BDSTextField(
                        modifier = Modifier
                            .align(Alignment.TopStart),
                        value = content,
                        onValueChange = { newValue ->
                            content = newValue
                            state =
                                if (content.isEmpty() || content.length > 200) BDSTextFieldState.Error else BDSTextFieldState.Focus
                        },
                        onFocusChanged = { focusState ->
                            state = if (focusState.isFocused) BDSTextFieldState.Focus else BDSTextFieldState.UnFocus
                        },
                        title = "내용을 작성해주세요 (선택)",
                        hint = "",
                        subText = "${content.length} / 최대 200자",
                        state = state
                    )
                    Row(modifier = Modifier.align(Alignment.BottomStart)) {
                        BDSSwitch(
                            modifier = Modifier,
                            checked = checked,
                            onCheckedChange = {
                                checked = !checked
                            })
                        BDSText(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .align(CenterVertically),
                            text = "비공개 (링크를 받은 친구만 투표 가능)",
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = BDSColor.SlateGray900,
                        )
                    }
                }
            }
        },
        bottomContent = {
            BDSBottomSheetVerticalDualButton(
                confirmButton = {
                    BDSFilledButton(
                        onClick = {
                            viewModel.setCurrentPostContent(content)
                            viewModel.setCurrentPostPublic(checked)
                            coroutineScope.launch() {
                                if (viewModel.selectedPost.value != null) {
                                    viewModel.postPublishPost()
                                } else {
                                    viewModel.postNewPost()
                                }
                            }
                            onClickNext()
                        },
                        text = "작성 완료",
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(),
                        contentPadding = BDSButtonInnerPadding.MEDIUM,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
                },
                cancelButton = {
                    BDSBorderlessButton(
                        onClick = {
                            coroutineScope.launch {
                                if (viewModel.selectedPost.value != null) {
                                    viewModel.postPublishPost()
                                } else {
                                    viewModel.postNewPost()
                                }
                            }
                            onClickNext()
                        },
                        text = "건너뛰기",
                        modifier = Modifier.height(34.dp),
                        contentPadding = BDSButtonInnerPadding.SMALL,
                        contentColor = BDSColor.Primary700,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                    )
                }
            )
        }
    )
}