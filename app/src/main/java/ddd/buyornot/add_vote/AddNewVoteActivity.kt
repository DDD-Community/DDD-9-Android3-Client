package ddd.buyornot.add_vote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSAppBar
import com.ddd.component.BDSImage
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BuyOrNotTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewVoteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BuyOrNotTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = BDSColor.White)
                ) {
                    var isTempStorageAvailable by remember {
                        mutableStateOf(false)
                    }

                    BDSAppBar(
                        modifier = Modifier
                            .fillMaxWidth(),
                        left = {
                            BDSText(
                                text = "취소",
                                modifier = Modifier.clickable {

                                },
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
                }
            }
        }
    }
}

@Composable
private fun VoteItemButton(
    modifier: Modifier,
    imageUrl: String?,
) {
    if (imageUrl.isNullOrBlank()) {
        Column(
            modifier = modifier
                .border(width = 1.dp, color = BDSColor.Primary200, shape = RoundedCornerShape(size = 16.dp)),
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