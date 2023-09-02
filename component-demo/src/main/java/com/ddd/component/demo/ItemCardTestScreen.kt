package com.ddd.component.demo

import androidx.compose.runtime.Composable
import com.ddd.component.ArchiveItem
import com.ddd.component.BDSArchiveItemCard

@Composable
fun ItemCardTestScreen() {
    val item = ArchiveItem(
        "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
        "마르디메르크디",
        "SWEATSHIRT FLOWERMARDI_OATME..",
        20,
        67500
    )

    BDSArchiveItemCard(
        archiveItem = item
    )
}