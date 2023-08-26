package ddd.buyornot.add_vote.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSAppBar
import com.ddd.component.BDSText
import com.ddd.component.BDSTextField
import com.ddd.component.BDSTextFieldState
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BuyOrNotTheme
import ddd.buyornot.add_vote.viewmodel.AddNewVoteViewModel

@Composable
fun AddNewVoteScreen(
    addNewVoteViewModel: AddNewVoteViewModel
) {
    BuyOrNotTheme {
        val activity = LocalContext.current as? Activity
        val scrollState = rememberScrollState()
        var isTempStorageAvailable by remember { mutableStateOf(false) }
        var bdsTextFieldState: BDSTextFieldState by remember {
            mutableStateOf(
                BDSTextFieldState.UnFocus
            )
        }
        var voteTitle by remember { mutableStateOf("") }
        var voteDescription by remember { mutableStateOf("") }
        var hideVote by remember { mutableStateOf(false) }

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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    VoteItemButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(start = 24.dp, end = 8.dp)
                            .weight(1f),
                        imageUrl = ""
                    )
                    VoteItemButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(start = 8.dp, end = 24.dp)
                            .weight(1f),
                        imageUrl = ""
                    )
                }

                BDSTextField(
                    modifier = Modifier.padding(
                        top = 36.dp,
                        start = 22.dp,
                        end = 22.dp,
                    ),
                    value = voteTitle,
                    onValueChange = {
                        voteTitle = it
                    },
                    title = "투표 제목을 작성해주세요",
                    hint = "",
                    subText = "${voteTitle.length} / 최대 30자",
                    maxLength = 30,
                    onFocusChanged = {
                        bdsTextFieldState =
                            if (it.isFocused) BDSTextFieldState.Focus else BDSTextFieldState.UnFocus
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                )

                BDSTextField(
                    modifier = Modifier.padding(
                        vertical = 24.dp,
                        horizontal = 22.dp
                    ),
                    value = voteDescription,
                    onValueChange = {
                        voteDescription = it
                    },
                    title = "내용을 작성해주세요 (선택)",
                    hint = "",
                    maxLength = 200,
                    subText = "${voteDescription.length} / 최대 200자",
                    onFocusChanged = {
                        bdsTextFieldState =
                            if (it.isFocused) BDSTextFieldState.Focus else BDSTextFieldState.UnFocus
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                )
            }

            VoteBottomContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 36.dp, top = 16.dp)
                    .align(Alignment.BottomCenter),
                checked = hideVote,
                savedCount = 0,
                postButtonEnabled = true,
                onCheckedChange = {
                    hideVote = it
                },
                onClickSave = {

                },
                onClickPost = {

                },
            )
        }
    }
}