package com.ddd.component.demo

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.ArchiveItem
import com.ddd.component.BDSArchiveItemCard
import com.ddd.component.BDSBorderlessButton
import com.ddd.component.BDSBottomNavigationLayout
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSHeader
import com.ddd.component.BDSImage
import com.ddd.component.BDSText
import com.ddd.component.BottomNavigationItem
import com.ddd.component.R
import com.ddd.component.theme.BDSColor.Gray900
import com.ddd.component.theme.BDSColor.SlateGray100
import com.ddd.component.theme.BDSColor.SlateGray500
import com.ddd.component.theme.BDSColor.SlateGray600
import com.ddd.component.theme.BDSColor.SlateGray900
import com.ddd.component.theme.BDSColor.White
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
fun ArchiveScreen() {
    val archiveItems = listOf(
        ArchiveItem(
            "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            "마르디메르크디",
            "SWEATSHIRT FLOWERMARDI_OATME..",
            20f,
            67500
        ), ArchiveItem(
            "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            "마르디메르크디",
            "SWEATSHIRT FLOWERMARDI_OATME..",
            20f,
            67500
        ), ArchiveItem(
            "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            "마르디메르크디",
            "SWEATSHIRT FLOWERMARDI_OATME..",
            20f,
            67500
        ), ArchiveItem(
            "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            "마르디메르크디",
            "SWEATSHIRT FLOWERMARDI_OATME..",
            20f,
            67500
        ), ArchiveItem(
            "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            "마르디메르크디",
            "SWEATSHIRT FLOWERMARDI_OATME..",
            20f,
            67500
        ), ArchiveItem(
            "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            "마르디메르크디",
            "SWEATSHIRT FLOWERMARDI_OATME..",
            20f,
            67500
        ), ArchiveItem(
            "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            "마르디메르크디",
            "SWEATSHIRT FLOWERMARDI_OATME..",
            20f,
            67500
        ), ArchiveItem(
            "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            "마르디메르크디",
            "SWEATSHIRT FLOWERMARDI_OATME..",
            20f,
            67500
        ), ArchiveItem(
            "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            "마르디메르크디",
            "SWEATSHIRT FLOWERMARDI_OATME..",
            20f,
            67500
        ), ArchiveItem(
            "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            "마르디메르크디",
            "SWEATSHIRT FLOWERMARDI_OATME..",
            20f,
            67500
        ), ArchiveItem(
            "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            "마르디메르크디",
            "SWEATSHIRT FLOWERMARDI_OATME..",
            20f,
            67500
        ), ArchiveItem(
            "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
            "마르디메르크디",
            "SWEATSHIRT FLOWERMARDI_OATME..",
            20f,
            67500
        )
    )

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var selectedBottomNavigation: BottomNavigationItem by remember { mutableStateOf(BottomNavigationItem.bottomNavigationItems[2]) }
    var isEmpty: Boolean by remember { mutableStateOf(false) }

    val state = rememberCollapsingToolbarScaffoldState()

    BDSBottomNavigationLayout(
        selectedNavigationItem = selectedBottomNavigation,
        onClickNavigationItem = {
            selectedBottomNavigation = it
        }
    ) {
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
                        SlateGray100
                    } else {
                        Gray900
                    }
                )

                Box(
                    modifier = Modifier
                        .background(color = White)
                        .fillMaxWidth()
                        .height(183.dp)
                        .pin()
                )
                BDSImage(
                    resId = com.ddd.component.demo.R.drawable.bg_archive,
                    modifier = Modifier
                        .parallax(0.8f)
                        .height(183.dp)
                        .alpha(imageAlpha)
                )
                BDSBorderlessButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(width = 45.dp, height = 24.dp),
                    contentPadding = BDSButtonInnerPadding.XSMALL,
                    text = "편집",
                    fontSize = 12.sp,
                    fontWeight = SemiBold,
                    enabled = archiveItems.isNotEmpty()
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
                    fontWeight = ExtraBold
                )
            }
        ) {
            Column {
                BDSHeader(
                    left = {
                        BDSText(
                            text = "담은 상품 ${archiveItems.size}",
                            modifier = Modifier.padding(start = 4.dp, top = 19.dp),
                            color = SlateGray500,
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            fontWeight = Bold,
                        )
                    },
                    right = {
                        BDSText(
                            text = "편집",
                            modifier = Modifier
                                .padding(end = 4.dp, top = 19.dp)
                                .clickable(enabled = !archiveItems.isNullOrEmpty(), onClick = { /* Edit 화면으로 이동 */ }),
                            color = SlateGray900,
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            fontWeight = SemiBold
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
                        fontWeight = Bold,
                        color = SlateGray900,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    BDSText(
                        text = "좋아요를 눌러 아카이브함을 채워보세요!",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = Medium,
                        color = SlateGray600,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Spacer(modifier = Modifier.height(4.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        items(archiveItems) { archiveItem ->
                            BDSArchiveItemCard(
                                archiveItem = archiveItem,
                                modifier = Modifier.clickable {
                                    /* VIP 이동 */
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}