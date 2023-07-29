package com.ddd.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.theme.BDSColor.SlateGray500
import com.ddd.component.theme.BDSColor.SlateGray900

// TODO: data class 위치 변경
data class PostItem(
    val imageUrl: String? = null,
    val title: String? = null,
    val isPublic: Boolean = false,
)

@Composable
fun BDSPostCard(
    postItem: PostItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
    ) {
        Spacer(
            modifier = Modifier
                .width(16.dp)
        )
        BDSImage(
            url = postItem.imageUrl,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(63.dp)
                .align(Alignment.CenterVertically),
        )
        Spacer(
            modifier = Modifier
                .width(12.dp)
        )
        Column(
            modifier = Modifier
                .width(206.dp)
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            BDSText(
                text = postItem.title,
                color = SlateGray900,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = SemiBold,
            )
            BDSText(
                text = if (postItem.isPublic) "공개 투표" else "비공개 투표",
                color = SlateGray500,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontWeight = SemiBold
            )
        }
        Spacer(modifier = Modifier.width(34.dp))
        /*BDSButton() {
            // 체크 버튼 추가
        }*/
    }
}