package ddd.buyornot.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.ArchiveItem
import com.ddd.component.BDSAppBar
import com.ddd.component.BDSArchiveItemCard
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSIconButton
import com.ddd.component.BDSImage
import com.ddd.component.BDSOutlinedButton
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor.Black
import com.ddd.component.theme.BDSColor.SlateGray500
import com.ddd.component.theme.BDSColor.SlateGray900
import ddd.buyornot.R
import ddd.buyornot.data.model.post.PostResult
import ddd.buyornot.home.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    // TODO: 리스트 fetch 개선, paging 추가 
    val postList by viewModel.postList.observeAsState(emptyList())

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            viewModel.fetchPostList()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                BDSAppBar(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    left = {
                        BDSImage(
                            resId = R.drawable.ic_text_logo,
                            modifier = Modifier.size(width = 116.dp, height = 23.dp),
                            contentScale = ContentScale.Fit
                        )
                    },
                    right = {
                        Row {
                            BDSIconButton(
                                resId = com.ddd.component.R.drawable.ic_archive_empty,
                                onClick = { /*TODO*/ }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            BDSIconButton(
                                // resId = user profile logo,
                                resId = com.ddd.component.R.drawable.ic_add_in_circle,
                                onClick = { /*TODO*/ }
                            )
                        }
                    },
                    center = null
                )
            }
        ) { paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(postList) { post ->
                    BDSHomeCard(
                        post = post,
                        patchPollChoice = viewModel::patchPollChoice,
                    )
                }
            }
        }
    }
}

@Composable
fun BDSHomeCard(
    post: PostResult,
    patchPollChoice: (Int, Int) -> Unit = { _, _ -> },
    isMyPost: Boolean = false,
    onClickDots: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val pollA = post.pollItemResponseList?.getOrNull(0) ?: return
    val pollB = post.pollItemResponseList?.getOrNull(1) ?: return

    Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 14.dp)) {
        UserCard(
            userNickname = post.userNickname,
            isVisible = isMyPost,
            onClick = onClickDots
        )
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
    isVisible: Boolean = false,
    onClick: () -> Unit
) {
    Box {
        Row(modifier = Modifier.align(Alignment.CenterStart)) {
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
        if (isVisible) {
            BDSIconButton(
                resId = com.ddd.component.R.drawable.ic_dots_mono,
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = onClick
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
