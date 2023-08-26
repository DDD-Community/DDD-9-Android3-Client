package ddd.buyornot.add_vote.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSImage
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor

@Composable
internal fun VoteItemButton(
    modifier: Modifier,
    imageUrl: String?,
) {
    if (imageUrl.isNullOrBlank()) {
        Column(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = BDSColor.Primary200,
                    shape = RoundedCornerShape(size = 16.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BDSImage(
                resId = com.ddd.component.R.drawable.ic_round_add,
                tintColor = BDSColor.Primary500,
                modifier = Modifier.size(38.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            BDSText(
                text = "투표에 올릴 상품 등록",
                fontSize = 12.sp,
                color = BDSColor.Primary500,
            )
        }
    } else {
        BDSImage(
            modifier = modifier
                .background(BDSColor.Gray100, shape = RoundedCornerShape(size = 16.dp)),
            url = imageUrl,
            contentScale = ContentScale.Crop
        )
    }
}