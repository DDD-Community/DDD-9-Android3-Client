package com.ddd.component.demo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.ArchiveItem
import com.ddd.component.BDSAppBar
import com.ddd.component.BDSArchiveItemCard
import com.ddd.component.BDSButton
import com.ddd.component.BDSEditBottomNavigationLayout
import com.ddd.component.BDSHeader
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchiveEditScreen() {
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
    val clickEnabled by remember { mutableStateOf(false) }

    BDSEditBottomNavigationLayout(
        clickEnabled = clickEnabled,
    ) {
        Scaffold(
            topBar = {
                BDSAppBar(
                    title = "상품 선택",
                    right = {
                        BDSButton {
                            // 완료 버튼
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                BDSHeader(
                    left = {
                        BDSText(
                            text = "담은 상품 ${archiveItems.size}",
                            modifier = Modifier.padding(start = 4.dp, top = 19.dp),
                            color = BDSColor.SlateGray500,
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                )
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
                                /* 아이템 check */
                            }
                        )
                    }
                }
            }
        }
    }
}