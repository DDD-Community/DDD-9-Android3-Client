package ddd.buyornot.postpage.ui.bottomsheet

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSBorderlessButton
import com.ddd.component.BDSBottomSheet
import com.ddd.component.BDSBottomSheetHeader
import com.ddd.component.BDSBottomSheetVerticalDualButton
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSImage
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor

@Composable
fun WritePostPageDoneBottomSheet(
    onDismissRequest: () -> Unit
) {
    BDSBottomSheet(
        onDismissRequest = onDismissRequest,
        headerContent = {
            BDSBottomSheetHeader(
                center = {
                    BDSText(
                        text = "투표를 완성했어요!",
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BDSColor.SlateGray900
                    )
                }
            )
        },
        bodyContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 138.dp, height = 125.dp)
                        .align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    BDSImage(
                        url = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
                        modifier = Modifier
                            .size(91.dp, 91.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(width = 1.dp, color = BDSColor.SlateGray900)
                            .align(Alignment.TopStart)
                    )
                    BDSImage(
                        url = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
                        modifier = Modifier
                            .size(91.dp, 91.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(width = 1.dp, color = BDSColor.SlateGray900)
                            .align(Alignment.BottomEnd)
                    )
                }
            }
        },
        bottomContent = {
            BDSBottomSheetVerticalDualButton(
                confirmButton = {
                    BDSFilledButton(
                        onClick = { /*TODO*/ }, text = "앱에서 투표 완성하기",
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
                        onClick = { /*TODO*/ }, text = "닫기",
                        modifier = Modifier.size(width = 74.dp, height = 34.dp),
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