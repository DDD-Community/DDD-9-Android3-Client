package com.ddd.component.demo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSDivider
import com.ddd.component.BDSHeader
import com.ddd.component.BDSImage
import com.ddd.component.BDSText
import com.ddd.component.BodyView
import com.ddd.component.theme.BDSColor.Red
import com.ddd.component.theme.BDSColor.SlateGray300
import com.ddd.component.theme.BDSColor.SlateGray700
import com.ddd.component.theme.BDSColor.SlateGray800
import com.ddd.component.theme.BDSColor.SlateGray900
import com.ddd.component.theme.BDSColor.White

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BDSImage(
            url = "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            modifier = Modifier
                .fillMaxWidth()
                .height(183.dp),
            contentScale = ContentScale.Crop
        )
        BDSText(
            text = "익명의티셔츠234",
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 42.dp, bottom = 34.dp),
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = SemiBold,
            color = SlateGray900
        )
        BDSHeader(
            left = {
                BDSText(
                    text = "내 글 관리",
                    modifier = Modifier.padding(start = 4.dp),
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontWeight = Bold,
                    color = SlateGray700
                )
            }
        )
        BodyView(
            left = {
                BDSText(
                    text = "내가 쓴 글",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = SemiBold,
                    color = SlateGray800
                )
            },
            right = {
                BDSImage(
                    resId = com.ddd.component.R.drawable.ic_arrow_right_small_mono,
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        Spacer(modifier = Modifier.height(42.dp))
        BDSHeader(
            left = {
                BDSText(
                    text = "내 정보 관리",
                    modifier = Modifier.padding(start = 4.dp),
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontWeight = Bold,
                    color = SlateGray700
                )
            }
        )
        BodyView(
            left = {
                BDSText(
                    text = "로그아웃",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = SemiBold,
                    color = SlateGray800
                )
            }
        )
        BodyView(
            left = {
                BDSText(
                    text = "계정삭제",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = SemiBold,
                    color = Red
                )
            }
        )
    }
    BDSImage(
        resId = com.ddd.component.R.drawable.ic_app_logo_sample,
        modifier = Modifier
            .offset(32.dp, 153.dp)
            .clip(CircleShape)
            .size(56.dp)
            .border(2.dp, White, CircleShape)
    )
}

