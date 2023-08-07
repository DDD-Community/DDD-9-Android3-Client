package com.ddd.component.demo.write

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSBorderlessButton
import com.ddd.component.BDSBottomSheet
import com.ddd.component.BDSBottomSheetHeader
import com.ddd.component.BDSBottomSheetVerticalDualButton
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSIconButton
import com.ddd.component.BDSImage
import com.ddd.component.BDSText
import com.ddd.component.R
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BDSColor.SlateGray100
import com.ddd.component.theme.BDSColor.SlateGray300
import com.ddd.component.theme.BDSColor.SlateGray600
import com.ddd.component.theme.BDSColor.SlateGray900

@Composable
fun WritePostPageDefaultBottomSheet(
    onDismissRequest: () -> Unit
) {
    BDSBottomSheet(
        onDismissRequest = onDismissRequest,
        headerContent = {
            BDSBottomSheetHeader(
                center = {
                    BDSText(
                        text = "상품 옵션 선택",
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = SemiBold,
                        color = SlateGray900
                    )
                },
                right = {
                    BDSIconButton(resId = R.drawable.ic_ic_round_close, onClick = { /*TODO*/ })
                }
            )
        },
        bodyContent = {
            Column(modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 18.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SelectionButton(
                    resId = R.drawable.ic_write_colored,
                    title = "이 상품으로 투표 만들기",
                    subTitle = "바로 투표를 생성할 수 있어요",
                    {}
                )
                SelectionButton(
                    resId = R.drawable.ic_archive_colored,
                    title = "이 상품 아카이브에 담기",
                    subTitle = "아카이브함에서 상품을 확인할 수 있어요",
                    {}
                )
            }
        },
        bottomContent = {
            Spacer(modifier = Modifier.height(52.dp))
        }
    )
}

@Composable
fun SelectionButton(
    @DrawableRes resId: Int,
    title: String,
    subTitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = SlateGray100, shape = RoundedCornerShape(16.dp))
            .border(width = 1.dp, color = SlateGray300, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 26.dp, vertical = 32.dp)
    ) {
        BDSImage(
            modifier = Modifier.size(38.dp),
            resId = resId,
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(18.dp))
        Column(
            verticalArrangement = Arrangement.SpaceAround
        ) {
            BDSText(
                text = title,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = SemiBold,
                color = SlateGray900
            )
            BDSText(
                text = subTitle,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontWeight = SemiBold,
                color = SlateGray600
            )
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(38.dp)
        ) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.ic_arrow_right_small_mono),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun ArchivingSuccessBottomSheet(
    onDismissRequest: () -> Unit,
) {
    BDSBottomSheet(
        onDismissRequest = onDismissRequest,
        headerContent = {
            BDSBottomSheetHeader(
                center = {
                    BDSText(
                        text = "아카이브 담기를 완료했어요!",
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = SemiBold,
                        color = SlateGray900
                    )
                }
            )
        },
        bodyContent = {
            Box(
                modifier = Modifier.padding(top = 11.dp, start = 86.dp, end = 92.dp)
            ) {
                BDSImage(
                    resId = R.drawable.bg_archive_success,
                    contentScale = ContentScale.Fit
                )
            }
        },
        bottomContent = {
            BDSBottomSheetVerticalDualButton(
                confirmButton = {
                    BDSFilledButton(
                        onClick = { /*TODO*/ }, text = "앱에서 확인하기",
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