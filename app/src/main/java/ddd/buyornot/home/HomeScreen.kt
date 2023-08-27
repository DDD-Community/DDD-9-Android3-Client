package ddd.buyornot.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import ddd.buyornot.data.model.post.PostResult
import ddd.buyornot.home.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    val postList by viewModel.postList.observeAsState(emptyList())

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            viewModel.fetchPostList()
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            items(postList) { post ->
                HomeCard(
                    post = post,
                    patchPollChoice = viewModel::patchPollChoice
                )
            }
        }
    }
}

@Composable
fun HomeCard(
    post: PostResult,
    patchPollChoice: (Int, Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val pollA = post.pollItemResponseList?.getOrNull(0) ?: return
    val pollB = post.pollItemResponseList?.getOrNull(1) ?: return

    Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 14.dp)) {
        UserCard(userNickname = post.userNickname)
        Spacer(modifier = Modifier.height(10.dp))
        BDSText(
            text = post.title,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = SemiBold,
            color = Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        BDSText(
            text = post.content,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = Normal,
            color = Black
        )
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            BDSVoteCard(
                archiveItem = ArchiveItem(
                    imageUrl = pollA.imgUrl,
                    brand = pollA.brand,
                    name = pollA.itemName,
                    discount = pollA.discountedPrice,
                    price = pollA.originalPrice
                ),
                title = "A",
                onClick = {
                    scope.launch {
                        post.id?.let {
                            patchPollChoice(it, 1)
                        }
                    }
                }
            )
            BDSVoteCard(
                archiveItem = ArchiveItem(
                    imageUrl = pollB.imgUrl,
                    brand = pollB.brand,
                    name = pollB.itemName,
                    discount = pollB.discountedPrice,
                    price = pollB.originalPrice
                ),
                title = "B",
                onClick = {
                    scope.launch {
                        post.id?.let {
                            patchPollChoice(it, 2)
                        }
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            BDSFilledButton(
                onClick = {
                    scope.launch {
                        post.id?.let {
                            patchPollChoice(it, 0)
                        }
                    }
                },
                text = "둘다 별로"
            )
            BDSFilledButton(onClick = { /*TODO*/ }, text = "공유하기")
        }
    }

}

@Composable
private fun UserCard(
    userNickname: String?,
    // userImage: String?,
    // until: String?
) {
    Row {
        BDSImage(
            // url = userImage,
            resId = com.ddd.component.R.drawable.ic_app_logo_sample,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            BDSText(
                text = userNickname,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontWeight = SemiBold,
                color = SlateGray900
            )
            Spacer(modifier = Modifier.height(2.dp))
            BDSText(
                // text = until,
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
private fun BDSVoteCard(
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
