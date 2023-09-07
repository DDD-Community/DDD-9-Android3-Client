package ddd.buyornot.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.ArchiveItem
import com.ddd.component.BDSAppBar
import com.ddd.component.BDSArchiveItemCard
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSIconButton
import com.ddd.component.BDSImage
import com.ddd.component.BDSOutlinedButton
import com.ddd.component.BDSPollButton
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor.Black
import com.ddd.component.theme.BDSColor.Primary100
import com.ddd.component.theme.BDSColor.Primary500
import com.ddd.component.theme.BDSColor.Primary700
import com.ddd.component.theme.BDSColor.SlateGray300
import com.ddd.component.theme.BDSColor.SlateGray400
import com.ddd.component.theme.BDSColor.SlateGray500
import com.ddd.component.theme.BDSColor.SlateGray900
import ddd.buyornot.R
import ddd.buyornot.data.model.poll.PollResponse
import ddd.buyornot.data.model.post.PostResult
import ddd.buyornot.home.viewmodel.HomeViewModel
import ddd.buyornot.my_post.ui.MyPostActivity
import ddd.buyornot.profile.ui.ProfileActivity
import ddd.buyornot.util.openWeb
import ddd.buyornot.util.sharePostWeb
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val context = LocalContext.current
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
                        Row {
                            BDSImage(
                                resId = com.ddd.component.R.drawable.ic_app_mini,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(9.dp))
                            BDSImage(
                                resId = R.drawable.ic_text_logo,
                                modifier = Modifier.size(width = 116.dp, height = 23.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    },
                    right = {
                        Row {
                            BDSIconButton(
                                resId = com.ddd.component.R.drawable.ic_archive,
                                onClick = { context.startActivity(Intent(context, MyPostActivity::class.java)) }
                            )
                            Spacer(modifier = Modifier.width(9.dp))
                            BDSIconButton(
                                // resId = user profile logo,
                                resId = com.ddd.component.R.drawable.ic_add_in_circle,
                                onClick = { context.startActivity(Intent(context, ProfileActivity::class.java)) }
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
                        onClick = { itemUrl -> context.openWeb(itemUrl) }
                    )
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(color = SlateGray400)
                    )
                }
            }
        }
    }
}

private fun Int.calculatePollRate(other: Int): Float =
    when {
        this <= 0 && other <= 0 -> 0f
        other <= 0 -> 1f
        else -> this / (this + other).toFloat()
    }

@Composable
fun BDSHomeCard(
    post: PostResult,
    patchPollChoice: (Int, Int) -> Unit = { _, _ -> },
    isMyPost: Boolean = false,
    onClick: (String) -> Unit = {},
    onClickDots: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val pollA = post.pollItemResponseList?.getOrNull(0) ?: return
    val pollB = post.pollItemResponseList?.getOrNull(1) ?: return
    val a = post.pollResponse?.firstItem ?: 0
    val b = post.pollResponse?.secondItem ?: 0
    val x = post.pollResponse?.unrecommended ?: 0

    Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 14.dp)) {
        UserCard(
            userNickname = post.userNickname,
            userImage = post.userProfile,
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
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BDSPollCard(
                archiveItem = ArchiveItem(
                    imageUrl = pollA.imgUrl,
                    brand = pollA.brand,
                    name = pollA.itemName,
                    discount = pollA.discountedRate,
                    price = pollA.originalPrice
                ),
                pollStatus = post.pollStatus,
                title = if (post.participateStatus || post.pollStatus == PostResult.PollStatus.CLOSED) {
                    "A | ${(a.calculatePollRate(b) * 100).toInt()}%"
                } else {
                    "A"
                },
                participateStatus = post.participateStatus,
                pollRate = a.calculatePollRate(b),
                onClick = {
                    post.pollItemResponseList?.get(0)?.itemUrl?.let { onClick(it) }
                },
                onClickPoll = {
                    scope.launch {
                        post.id?.let {
                            pollA.id?.let { it1 -> patchPollChoice(it, it1) }
                        }
                    }
                }
            )
            BDSPollCard(
                archiveItem = ArchiveItem(
                    imageUrl = pollB.imgUrl,
                    brand = pollB.brand,
                    name = pollB.itemName,
                    discount = pollB.discountedRate,
                    price = pollB.originalPrice
                ),
                pollStatus = post.pollStatus,
                title = if (post.participateStatus || post.pollStatus == PostResult.PollStatus.CLOSED) {
                    "B | ${(b.calculatePollRate(a) * 100).toInt()}%"
                } else {
                    "B"
                },
                participateStatus = post.participateStatus,
                pollRate = b.calculatePollRate(a),
                onClick = {
                    post.pollItemResponseList?.get(1)?.itemUrl?.let { onClick(it) }
                },
                onClickPoll = {
                    scope.launch {
                        post.id?.let {
                            pollB.id?.let { it1 -> patchPollChoice(it, it1) }
                        }
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            BDSFilledButton(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(width = 86.dp, height = 32.dp),
                contentPadding = BDSButtonInnerPadding.XSMALL,
                containerColor = SlateGray300,
                contentColor = SlateGray900,
                onClick = {
                    scope.launch {
                        post.id?.let {
                            patchPollChoice(it, 0)
                        }
                    }
                },
                text = "둘다 별로",
                resId = com.ddd.component.R.drawable.ic_cloud_thunder,
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontWeight = SemiBold
            )
            BDSFilledButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(width = 86.dp, height = 32.dp),
                contentPadding = BDSButtonInnerPadding.XSMALL,
                containerColor = Primary100,
                contentColor = Primary700,
                onClick = { post.id?.let { context.sharePostWeb(postId = it) } },
                text = "공유하기",
                resId = com.ddd.component.R.drawable.ic_share,
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontWeight = SemiBold
            )
        }
    }

}

@Composable
private fun UserCard(
    userNickname: String?,
    userImage: String?,
    // until: String?
    isVisible: Boolean = false,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.align(Alignment.CenterStart)) {
            BDSImage(
                url = userImage,
                modifier = Modifier.size(38.dp)
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
private fun BDSPollCard(
    archiveItem: ArchiveItem,
    modifier: Modifier = Modifier,
    isLike: Boolean = false,
    participateStatus: Boolean = false,
    pollStatus: PostResult.PollStatus? = PostResult.PollStatus.ONGOING,
    pollResponse: PollResponse? = null,
    pollRate: Float = 0f,
    onClick: () -> Unit = {},
    onClickLike: () -> Unit = {},
    title: String,
    onClickPoll: () -> Unit = {}
) {
    Column {
        BDSArchiveItemCard(
            archiveItem = archiveItem,
            modifier = modifier,
            isLike = isLike,
            onClick = onClick,
            onClickLike = onClickLike,
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (participateStatus || pollStatus == PostResult.PollStatus.CLOSED) {
            BDSPollButton(
                modifier = Modifier.width(164.dp)
                    .height(46.dp),
                text = title,
                // isSelect = isSelect, // 사용자가 투표한 상품을 알아야할듯
                fontSize = 14.sp,
                lineHeight = 20.sp,
                onClick = onClickPoll,
                pollRate = pollRate,
                enabled = pollStatus == PostResult.PollStatus.ONGOING
            )
        } else {
            BDSOutlinedButton(
                modifier = Modifier.width(164.dp),
                text = title,
                onClick = onClickPoll,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                contentColor = Primary500,
                borderColor = SlateGray300,
                enabled = pollStatus == PostResult.PollStatus.ONGOING
            )
        }
    }
}
