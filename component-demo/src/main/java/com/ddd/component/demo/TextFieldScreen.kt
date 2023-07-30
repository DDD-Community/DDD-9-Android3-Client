package com.ddd.component.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import com.ddd.component.BDSTextField
import com.ddd.component.BDSTextFieldState

@Composable
fun TextFieldScreen() {
    Column {
        Box {
            var value by remember {
                mutableStateOf("")
            }
            val focusRequester = remember { FocusRequester() }
            var state: BDSTextFieldState by remember { mutableStateOf(BDSTextFieldState.UnFocus) }

            BDSTextField(
                value = value,
                onValueChange = { newValue ->
                    value = newValue
                    state = if (value.isEmpty())  BDSTextFieldState.Error else BDSTextFieldState.Focus
                },
                onFocusChanged = { focusState ->
                    state = if (focusState.isFocused) BDSTextFieldState.Focus else BDSTextFieldState.UnFocus
                },
                title = "내용을 작성해주세요(선택)",
                hint = "레인부츠 어떤 색이 에쁜가요?",
                subText = "text",
                state = state,
            )
        }

        Box {
            var value by remember {
                mutableStateOf("")
            }
            val focusRequester = remember { FocusRequester() }
            var state: BDSTextFieldState by remember { mutableStateOf(BDSTextFieldState.Disable) }

            BDSTextField(
                value = value,
                onValueChange = { newValue ->
                    value = newValue
                    if (newValue.isEmpty()) state = BDSTextFieldState.Error
                },
                onFocusChanged = { focusState ->
                    // state = if (focusState.isFocused) BDSTextFieldState.Focus else BDSTextFieldState.UnFocus
                },
                enabled = false,
                title = "내용을 작성해주세요(선택)",
                hint = "레인부츠 어떤 색이 에쁜가요?",
                subText = "text",
                state = state,
            )
        }
    }

}