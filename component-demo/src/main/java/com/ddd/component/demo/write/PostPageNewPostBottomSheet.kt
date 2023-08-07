package com.ddd.component.demo.write

import androidx.compose.foundation.border
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

@Composable
fun WritePostPageNewPostBottomSheet(
    onDismissRequest: () -> Unit
) {
    var value by remember {
        mutableStateOf("")
    }
    var state: BDSTextFieldState by remember { mutableStateOf(BDSTextFieldState.UnFocus) }

    BDSBottomSheet(
        onDismissRequest = onDismissRequest,
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
                BDSImage(
                    url = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
                    modifier = Modifier
                        .size(91.dp, 91.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(width = 1.dp, color = BDSColor.SlateGray900)
                )
                Spacer(modifier = Modifier.height(16.dp))
                BDSTextField(
                    value = value,
                    onValueChange = { newValue ->
                        value = newValue
                        state = if (value.isEmpty() || value.length > 30) BDSTextFieldState.Error else BDSTextFieldState.Focus
                    },
                    onFocusChanged = { focusState ->
                        state = if (focusState.isFocused) BDSTextFieldState.Focus else BDSTextFieldState.UnFocus
                    },
                    title = "투표 제목을 작성해주세요",
                    hint = "",
                    subText = "${value.length} / 최대 30자",
                    state = state
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        bottomContent = {
            BDSBottomSheetSingleButton {
                BDSFilledButton(
                    onClick = { /*TODO*/ },
                    text = "다음으로",
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = BDSButtonInnerPadding.MEDIUM,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    enabled = !(state is BDSTextFieldState.Error)
                )
            }
        }
    )
}