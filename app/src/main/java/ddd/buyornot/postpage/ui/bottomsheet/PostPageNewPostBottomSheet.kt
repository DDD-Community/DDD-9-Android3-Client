package ddd.buyornot.postpage.ui.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSBottomSheet
import com.ddd.component.BDSBottomSheetHeader
import com.ddd.component.BDSBottomSheetSingleButton
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSImage
import com.ddd.component.BDSText
import com.ddd.component.BDSTextField
import com.ddd.component.BDSTextFieldState
import com.ddd.component.theme.BDSColor
import ddd.buyornot.postpage.viewmodel.ShareViewModel

@Composable
fun WritePostPageNewPostBottomSheet(
    viewModel: ShareViewModel,
    onDismissRequest: () -> Unit,
    onClickNext: () -> Unit,
) {
    val savedTitle = viewModel.currentPost.title ?: ""
    var title by remember { mutableStateOf(savedTitle) }

    var state: BDSTextFieldState by remember { mutableStateOf(BDSTextFieldState.UnFocus) }

    BDSBottomSheet(
        onDismissRequest = onDismissRequest,
        limitHeight = false,
        headerContent = {
            BDSBottomSheetHeader(
                center = {
                    BDSText(
                        text = "새 투표 만들기",
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
                    .padding(horizontal = 22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                if (viewModel.sharedItemImageUrl != null) {
                    BDSImage(
                        url = viewModel.sharedItemImageUrl,
                        modifier = Modifier
                            .size(91.dp, 91.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                } else {
                    BDSImage(
                        resId = com.ddd.component.R.drawable.ic_unknown_item,
                        modifier = Modifier
                            .size(91.dp, 91.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                BDSTextField(
                    modifier = Modifier.padding(8.dp),
                    value = title,
                    onValueChange = { newValue ->
                        title = newValue
                        state = if (title.isEmpty() || title.length > 30) BDSTextFieldState.Error else BDSTextFieldState.Focus
                    },
                    onFocusChanged = { focusState ->
                        state = if (focusState.isFocused) BDSTextFieldState.Focus else BDSTextFieldState.UnFocus
                    },
                    title = "투표 제목을 작성해주세요",
                    hint = "",
                    subText = "${title.length} / 최대 30자",
                    state = state
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        bottomContent = {
            BDSBottomSheetSingleButton {
                BDSFilledButton(
                    onClick = {
                        viewModel.setCurrentPostTitle(title)
                        onClickNext()
                    },
                    text = "다음으로",
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = BDSButtonInnerPadding.MEDIUM,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    enabled = state !is BDSTextFieldState.Error && title.isNotEmpty()
                )
            }
        }
    )
}