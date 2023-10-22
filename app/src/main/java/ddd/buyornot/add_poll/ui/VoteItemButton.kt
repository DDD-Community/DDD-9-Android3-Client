package ddd.buyornot.add_poll.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSImage
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PollItemButton(
    modifier: Modifier,
    imageUrl: String?,
) {
    val context = LocalContext.current
    if (imageUrl.isNullOrBlank()) {
        Column(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = BDSColor.Primary200,
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .clickable {
                    // do nothing
                },
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