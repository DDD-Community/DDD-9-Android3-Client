package com.ddd.component.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSEditBottomNavigationLayout
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSHeader
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BDSColor.Gray900

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
        // TODO: 추후 수정
        onClickWrite = {},
        onClickDelete = {},
        selectCount = 3,
    ) {
        Scaffold(
            topBar = {
                BDSAppBar(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    right = {
                        BDSFilledButton(
                            onClick = { /*TODO*/ },
                            text = "완료",
                            modifier = Modifier.size(width = 64.dp, height = 34.dp),
                            contentPadding = BDSButtonInnerPadding.SMALL,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            // enabled = 선택된 아이템이 하나 이상일 때 true
                        )
                    },
                    center = {
                        BDSText(text = "상품 선택",
                            fontSize = 18.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Gray900
                        )
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
                            onClick = { },
                            isEditMode = true,
                            isSelected = true,
                        )
                    }
                }
            }
        }
    }
}
