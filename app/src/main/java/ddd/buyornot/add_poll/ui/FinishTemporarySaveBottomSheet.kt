package ddd.buyornot.add_poll.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSBottomSheet
import com.ddd.component.BDSBottomSheetVerticalDualButton
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor

@Composable
@Preview
fun FinishNewPollScreen() {
    BDSBottomSheet(
        limitHeight = false,
        bodyContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 34.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    BDSText(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "임시 저장이 완료되었습니다!",
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BDSColor.SlateGray900,
                        textAlign = TextAlign.Center
                    )
                    BDSText(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .fillMaxWidth(),
                        text = "임시 저장한 글은 언제든지 불러올 수 있어요",
                        fontSize = 16.sp,
                        lineHeight = 4.sp,
                        color = BDSColor.SlateGray600,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        bottomContent = {
            BDSBottomSheetVerticalDualButton(
                confirmButton = {
                    BDSFilledButton(
                        onClick = {

                        },
                        text = "확인",
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .fillMaxWidth(),
                        contentPadding = BDSButtonInnerPadding.MEDIUM,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        containerColor = BDSColor.Black
                    )
                },
            )
        }
    )
}