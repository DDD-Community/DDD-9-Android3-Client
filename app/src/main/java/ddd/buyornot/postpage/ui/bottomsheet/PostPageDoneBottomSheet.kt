package ddd.buyornot.postpage.ui.bottomsheet

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.platform.LocalContext
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
import ddd.buyornot.postpage.viewmodel.ShareViewModel
import ddd.buyornot.util.findActivity

@Composable
fun WritePostPageDoneBottomSheet(
    title: String,
    viewModel: ShareViewModel,
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current

    BDSBottomSheet(
        onDismissRequest = onDismissRequest,
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
                    if (viewModel.currentPostItemImageUrl != null) {
                        BDSImage(
                            url = viewModel.currentPostItemImageUrl,
                            modifier = Modifier
                                .size(91.dp, 91.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .align(Alignment.TopStart)
                        )
                        if (viewModel.sharedItemImageUrl != null) {
                            BDSImage(
                                url = viewModel.sharedItemImageUrl,
                                modifier = Modifier
                                    .size(91.dp, 91.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .align(Alignment.BottomEnd)
                            )
                        } else {
                            BDSImage(
                                resId = com.ddd.component.R.drawable.ic_unknown_item,
                                modifier = Modifier
                                    .size(91.dp, 91.dp)
                                    .align(Alignment.BottomEnd)
                            )
                        }
                    } else if (viewModel.sharedItemImageUrl != null) {
                        BDSImage(
                            url = viewModel.sharedItemImageUrl,
                            modifier = Modifier
                                .size(91.dp, 91.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .align(Alignment.TopStart)
                        )
                        BDSImage(
                            resId = com.ddd.component.R.drawable.ic_unknown_item,
                            modifier = Modifier
                                .size(91.dp, 91.dp)
                                .align(Alignment.BottomEnd)
                        )
                    } else {
                        BDSImage(
                            resId = com.ddd.component.R.drawable.ic_unknown_item,
                            modifier = Modifier
                                .size(91.dp, 91.dp)
                                .align(Alignment.TopStart)
                        )
                        BDSImage(
                            resId = com.ddd.component.R.drawable.ic_unknown_item,
                            modifier = Modifier
                                .size(91.dp, 91.dp)
                                .align(Alignment.BottomEnd)
                        )
                    }
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
                        onClick = { onDismissRequest() }, text = "닫기",
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

    BackHandler {
        context.findActivity().finish()
    }
}