package ddd.buyornot.add_poll.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSButton
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSImage
import com.ddd.component.BDSText
import ddd.buyornot.util.findActivity
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BuyOrNotTheme
import ddd.buyornot.R
import ddd.buyornot.add_poll.viewmodel.AddNewPollViewModel

@Composable
fun FinishNewPollScreen(
    viewModel: AddNewPollViewModel
) {
    val activity = LocalContext.current.findActivity()

    BuyOrNotTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = BDSColor.White)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 80.dp)
            ) {
                BDSText(
                    modifier = Modifier.padding(bottom = 36.dp),
                    text = "두근두근,\n투표가 올라갔어요!",
                    color = BDSColor.SlateGray900,
                    fontSize = 24.sp,
                    lineHeight = 36.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                )
                BDSImage(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    resId = R.drawable.graphic_finish_add_poll,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp),
            ) {
                BDSButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    text = "올린 투표 확인하기",
                    contentPadding = BDSButtonInnerPadding.MEDIUM,
                    fontWeight = FontWeight.SemiBold
                ) {

                }
                BDSButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 22.dp, top = 18.dp),
                    text = "닫기",
                    contentColor = BDSColor.Primary700,
                    containerColor = BDSColor.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                ) {
                    activity.finish()
                }
            }
        }
    }
}