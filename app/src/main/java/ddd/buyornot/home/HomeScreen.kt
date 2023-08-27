package ddd.buyornot.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.ArchiveItem
import com.ddd.component.BDSArchiveItemCard
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSImage
import com.ddd.component.BDSOutlinedButton
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor.Black
import com.ddd.component.theme.BDSColor.SlateGray500
import com.ddd.component.theme.BDSColor.SlateGray900
import ddd.buyornot.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {

    }
}

@Composable
fun HomeCard(viewModel: HomeViewModel) {
    Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 14.dp)) {
        UserCard()
        Spacer(modifier = Modifier.height(10.dp))
        BDSText(
            text = "레인부츠 색상 완전 고민 ㅠㅠ",
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = SemiBold,
            color = Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        BDSText(
            text = "첫번째는 6만원이고 두번째는 14만원인데 많이 차이가 나나? 궁금해 어떤게 색상이 예쁜지 잘 모르겠어 ㅠㅠ",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = Normal,
            color = Black
        )
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            /*BDSVoteCard(
                archiveItem = ,
                title =
            )
            BDSVoteCard(
                archiveItem = ,
                title =
            )*/
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            BDSFilledButton(onClick = { /*TODO*/ }, text = "둘다 별로")
            BDSFilledButton(onClick = { /*TODO*/ }, text = "공유하기")
        }
    }

}

@Composable
fun UserCard() {
    Row {
        BDSImage(
            resId = com.ddd.component.R.drawable.ic_app_logo_sample,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            BDSText(
                text = "익명의 카피바라",
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontWeight = SemiBold,
                color = SlateGray900
            )
            Spacer(modifier = Modifier.height(2.dp))
            BDSText(
                text = "1시간 전",
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontWeight = Normal,
                color = SlateGray500
            )
        }
    }
}

@Composable
fun BDSVoteCard(
    archiveItem: ArchiveItem,
    modifier: Modifier = Modifier,
    isLike: Boolean = false,
    onClickLike: () -> Unit = {},
    title: String,
    onClick: () -> Unit = {}
) {
    Column {
        BDSArchiveItemCard(
            archiveItem = archiveItem,
            modifier = modifier,
            isLike = isLike,
            onClickLike = onClickLike,
        )
        Spacer(modifier = Modifier.height(16.dp))
        BDSOutlinedButton(
            text = title,
            onClick = onClick
        )
    }
}
