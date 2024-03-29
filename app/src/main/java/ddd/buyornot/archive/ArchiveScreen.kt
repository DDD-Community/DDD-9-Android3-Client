package ddd.buyornot.archive

import android.content.Intent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSArchiveItemCard
import com.ddd.component.BDSBorderlessButton
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSHeader
import com.ddd.component.BDSImage
import com.ddd.component.BDSText
import com.ddd.component.R
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BDSColor.SlateGray900
import ddd.buyornot.archive.viewmodel.ArchiveViewModel
import ddd.buyornot.util.openWeb
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ExperimentalToolbarApi
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class, ExperimentalToolbarApi::class)
fun ArchiveScreen(
    viewModel: ArchiveViewModel
) {
    val context = LocalContext.current

    val archiveItems by viewModel.archiveItemList.observeAsState(emptyList())

    val state = rememberCollapsingToolbarScaffoldState()
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    // TODO: fetch 조건 변경
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            viewModel.fetchArchiveItemList(true)
        }
    }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = viewModel.isRefresh.value,
        onRefresh = viewModel::refresh
    )

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
        state = state,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            val textSize = (18 + (36 - 18) * state.toolbarState.progress).sp
            val verticalPadding = (20 + (32 - 20) * state.toolbarState.progress).dp
            val imageAlpha = state.toolbarState.progress
            val color: Color by animateColorAsState(
                if (state.toolbarState.progress > 0.5f) {
                    BDSColor.SlateGray100
                } else {
                    BDSColor.Gray900
                }
            )

            Box(
                modifier = Modifier
                    .background(color = BDSColor.White)
                    .fillMaxWidth()
                    .height(183.dp)
                    .pin()
            )
            BDSImage(
                resId = ddd.buyornot.R.drawable.bg_archive,
                modifier = Modifier
                    .parallax(0.8f)
                    .height(183.dp)
                    .alpha(imageAlpha)
            )
            BDSText(
                text = "아카이브함",
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .padding(vertical = verticalPadding)
                    .road(Alignment.Center, Alignment.BottomStart),
                color = color,
                fontSize = textSize,
                lineHeight = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    ) {
        Column {
            BDSHeader(
                modifier = Modifier.padding(top = 14.dp, bottom = 8.dp),
                left = {
                    BDSBorderlessButton(
                        onClick = {},
                        contentPadding = BDSButtonInnerPadding.XSMALL,
                        text = "담은 상품 ${archiveItems.size}",
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        enabled = false
                    )
                },
                right = {
                    BDSBorderlessButton(
                        onClick = {
                            context.startActivity(
                                Intent(context, ArchiveEditActivity::class.java)
                            )
                        },
                        contentPadding = BDSButtonInnerPadding.XSMALL,
                        text = "편집",
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        contentColor = SlateGray900,
                        enabled = archiveItems.isNotEmpty()
                    )
                }
            )
            Box {
                if (archiveItems.isEmpty()) {
                    Column(modifier = Modifier.verticalScroll(scrollState, true)) {
                        Spacer(modifier = Modifier.height(132.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            BDSImage(
                                resId = R.drawable.ic_archive_empty,
                                modifier = Modifier
                                    .size(77.dp)
                                    .align(Alignment.Center)
                            )
                        }
                        Spacer(modifier = Modifier.height(29.dp))
                        BDSText(
                            text = "아카이브함에 담긴 상품이 없어요",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = BDSColor.SlateGray900,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        BDSText(
                            text = "하트를 눌러 아카이브함을 채워보세요!",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = BDSColor.SlateGray600,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        items(archiveItems) { archiveItem ->
                            BDSArchiveItemCard(
                                archiveItem = archiveItem,
                                isEditMode = false,
                                isLike = true,
                                onClick = { archiveItem.itemUrl?.let { context.openWeb(it) } },
                                onClickLike = {
                                    scope.launch {
                                        viewModel.patchArchiveItemDelete(listOf(archiveItem))
                                    }
                                }
                            )
                        }
                    }
                }
                PullRefreshIndicator(
                    refreshing = viewModel.isRefresh.value,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                    backgroundColor = BDSColor.Primary400,
                )
            }
        }
    }
}