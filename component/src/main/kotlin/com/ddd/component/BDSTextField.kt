package com.ddd.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.theme.BDSColor.Gray400
import com.ddd.component.theme.BDSColor.Gray500
import com.ddd.component.theme.BDSColor.Gray600
import com.ddd.component.theme.BDSColor.Primary500
import com.ddd.component.theme.BDSColor.Red
import com.ddd.component.theme.BDSColor.SlateGray600
import com.ddd.component.theme.BDSColor.SlateGray800
import com.ddd.component.theme.BDSColor.SlateGray900

@Composable
fun BDSTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    title: String,
    hint: String,
    subText: String,
    enabled: Boolean = true,
    maxLength: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    state: BDSTextFieldState = BDSTextFieldState.UnFocus
) {
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {
        BDSText(
            text = title,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            fontWeight = Bold,
            color = state.titleColor
        )
        Spacer(modifier = Modifier.height(10.dp))
        BasicTextField(
            value = value,
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged(onFocusChanged = onFocusChanged),
            enabled = enabled,
            textStyle = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = state.valueColor
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    BDSText(hint)
                }
                innerTextField()
            },
            maxLines = 2,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            modifier = Modifier
                .height(2.dp),
            color = state.dividerColor
        )
        Spacer(modifier = Modifier.height(10.dp))
        BDSText(
            text = subText,
            modifier = Modifier.align(Alignment.End),
            fontSize = 12.sp,
            lineHeight = 18.sp,
            color = state.subTextColor
        )
    }
}

sealed class BDSTextFieldState(
    val titleColor: Color,
    val valueColor: Color,
    val dividerColor: Color,
    val subTextColor: Color
) {
    object Focus : BDSTextFieldState(
        titleColor = SlateGray800,
        valueColor = SlateGray900,
        dividerColor = Primary500,
        subTextColor = SlateGray800
    )

    object UnFocus : BDSTextFieldState(
        titleColor = SlateGray800,
        valueColor = Gray500,
        dividerColor = Gray600,
        subTextColor = SlateGray600
    )

    object Error : BDSTextFieldState(
        titleColor = Red,
        valueColor = SlateGray900,
        dividerColor = Red,
        subTextColor = Red

    )

    object Disable : BDSTextFieldState(
        titleColor = Gray400,
        valueColor = Gray500,
        dividerColor = Gray400,
        subTextColor = SlateGray800
    )
}