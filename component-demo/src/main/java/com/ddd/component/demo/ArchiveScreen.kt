package com.ddd.component.demo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.ArchiveItem
import com.ddd.component.BDSAppBar
import com.ddd.component.BDSArchiveItemCard
import com.ddd.component.BDSBottomNavigationLayout
import com.ddd.component.BDSDivider
import com.ddd.component.BDSImage
import com.ddd.component.BDSText
import com.ddd.component.BottomNavigationItem
import com.ddd.component.theme.BDSColor.SlateGray300
import com.ddd.component.theme.BDSColor.SlateGray500
import com.ddd.component.theme.BDSColor.SlateGray900
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
    var isSelectMode: Boolean by remember { mutableStateOf(false) }
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
                if (isSelectMode) {
                    BDSAppBar(
                        title = "상품 선택",
                        right = {
                            /* 완료 버튼 */
                        }
                    )
                } else {
                    val textSize = (18 + (30 - 18) * state.toolbarState.progress).sp
                    val verticalPadding = (20 + (32 - 20) * state.toolbarState.progress).dp
                    val imageAlpha = (1 * state.toolbarState.progress)

                    Box(
                        modifier = Modifier
                            .background(color = SlateGray500)
                            .fillMaxWidth()
                            .height(183.dp)
                            .pin()
                    )
                    BDSImage(
                        url = "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
                        modifier = Modifier
                            .height(183.dp)
                            .parallax(0.8f)
                            .alpha(imageAlpha),
                        contentScale = ContentScale.Crop
                    )
                    BDSText(
                        text = "아카이브함",
                        modifier = Modifier
                            .padding(horizontal = 18.dp)
                            .padding(vertical = verticalPadding)
                            .road(Alignment.Center, Alignment.BottomStart),
                        color = SlateGray900,
                        fontSize = textSize,
                        lineHeight = 32.sp,
                        fontWeight = ExtraBold
                    )
                }
            }
        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 19.dp, bottom = 12.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BDSText(
                        text = "담은 상품 ${archiveItems.size}",
                        color = SlateGray500,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    BDSText(
                        text = "편집",
                        color = SlateGray900,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                BDSDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = SlateGray300
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    items(archiveItems) { archiveItem ->
                        BDSArchiveItemCard(
                            archiveItem = archiveItem,
                            modifier = Modifier.combinedClickable(
                                onClick = {
                                    /* VIP 이동 */
                                },
                                onLongClick = {
                                    isSelectMode = !isSelectMode
                                }
                            ),
                            isSelectMode = isSelectMode
                        )
                    }
                }
            }
        }
    }
}