package ddd.buyornot.archive

import android.content.Intent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.ddd.component.BDSTab
import com.ddd.component.BDSText
import com.ddd.component.BottomNavigationItem
import com.ddd.component.R
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BDSColor.SlateGray900
import ddd.buyornot.archive.viewmodel.ArchiveViewModel
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
fun ArchiveScreen(
    viewModel: ArchiveViewModel
) {
    val context = LocalContext.current

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var selectedBottomNavigation: BottomNavigationItem by remember { mutableStateOf(BottomNavigationItem.bottomNavigationItems[2]) }
    var isEmpty: Boolean by remember { mutableStateOf(false) }

    val tabIndex by viewModel.tabIndex.observeAsState(0)

    val likedItems by viewModel.likedItemList.observeAsState(emptyList())
    val savedItems by viewModel.savedItemList.observeAsState(emptyList())

    val archiveItems by remember {
        mutableStateOf(
            when (tabIndex) {
                0 -> likedItems
                1 -> savedItems
                else -> emptyList()
            }
        )
    }

    val state = rememberCollapsingToolbarScaffoldState()
    val scope = rememberCoroutineScope()

    // TODO: fetch 조건 변경
    LaunchedEffect(key1 = tabIndex) {
        scope.launch {
            when (tabIndex) {
                0 -> viewModel.fetchLikedItemList()
                1 -> viewModel.fetchSavedItemList()
                else -> {}
            }
        }
    }

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            val textSize = (18 + (36 - 18) * state.toolbarState.progress).sp
            val verticalPadding = (20 + (32 - 20) * state.toolbarState.progress).dp
            val imageAlpha = (1 * state.toolbarState.progress)
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
            BDSTab(
                titles = listOf("좋아요한 상품", "저장한 상품"),
                selectedTabIndex = tabIndex,
                onTabSelected = {
                    viewModel.setTabIndex(it)
                }
            )
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
                            context.startActivity(Intent(context, ArchiveEditActivity::class.java))
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
            if (archiveItems.isEmpty()) {
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
                    text = "좋아요를 눌러 아카이브함을 채워보세요!",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = BDSColor.SlateGray600,
                    textAlign = TextAlign.Center
                )
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
                            isLike = archiveItem.liked,
                            onClickLike = {
                                scope.launch {
                                    viewModel.patchArchiveItemLike(archiveItem)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}