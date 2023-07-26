package ddd.buyornot.archive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.ArchiveItem
import com.ddd.component.BDSAppBar
import com.ddd.component.BDSArchiveItemCard
import com.ddd.component.BDSBorderlessButton
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSConfirmDialog
import com.ddd.component.BDSEditBottomNavigationLayout
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSHeader
import com.ddd.component.BDSOutlinedButton
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor
import ddd.buyornot.findActivity
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun ArchiveEditScreen() {
    val context = LocalContext.current

    val archiveItems = remember {
        mutableStateListOf(
            ArchiveItem(
                "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
                "마르디메르크디",
                "SWEATSHIRT",
                20f,
                67500
            ),
            ArchiveItem(
                "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
                "마르디메르크디",
                "SWEATSHIRT F.",
                20f,
                67500
            ),
            ArchiveItem(
                "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
                "마르디메르크디",
                "SWEATSHIRT FL",
                20f,
                67500
            ),
            ArchiveItem(
                "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
                "마르디메르크디",
                "SWEATSHIRT FLO",
                20f,
                67500
            ),
            ArchiveItem(
                "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
                "마르디메르크디",
                "SWEATSHIRT FLOW",
                20f,
                67500
            ),
            ArchiveItem(
                "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
                "마르디메르크디",
                "SWEATSHIRT FLOWE",
                20f,
                67500
            ),
            ArchiveItem(
                "https://cdn.newspenguin.com/news/photo/202112/10182_30193_258.jpg",
                "마르디메르크디",
                "SWEATSHIRT FLOWER",
                20f,
                67500
            ),
        )
    }
    val selectItems = remember { mutableStateListOf<ArchiveItem>() }
    val scope = rememberCoroutineScope()
    var showDeleteDialog by remember { mutableStateOf(false) }

    BDSEditBottomNavigationLayout(
        selectCount = selectItems.size,
        onClickWrite = {},
        onClickDelete = { showDeleteDialog = true }
    ) {
        Scaffold(
            topBar = {
                BDSAppBar(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    right = {
                        BDSFilledButton(
                            onClick = { context.findActivity().finish() },
                            text = "완료",
                            modifier = Modifier.size(width = 64.dp, height = 34.dp),
                            contentPadding = BDSButtonInnerPadding.SMALL,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                        )
                    },
                    center = {
                        BDSText(
                            text = "상품 선택",
                            fontSize = 18.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = BDSColor.Gray900
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
                        archiveItem.run {
                            BDSArchiveItemCard(
                                archiveItem = this,
                                onClickSelect = {
                                    isSelected.value = !isSelected.value
                                    if (isSelected.value) selectItems.add(this) else selectItems.remove(this)
                                },
                                isEditMode = true,
                                isSelected = isSelected.value,
                            )
                        }
                    }
                }
            }
        }
    }

    if (showDeleteDialog) {
        val sheetState: SheetState = rememberModalBottomSheetState()
        BDSConfirmDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = "아카이브의 상품을 삭제할까요?",
            subTitle = "삭제된 상품은 아카이브함에서 볼 수 없어요.",
            sheetState = sheetState,
            cancelButton = {
                BDSOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showDeleteDialog = false
                            }
                        }
                    },
                    text = "취소"
                )
            },
            acceptButton = {
                BDSFilledButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        scope.launch {
                            archiveItems.removeAll(selectItems)
                            selectItems.clear()
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showDeleteDialog = false
                            }
                        }
                    },
                    text = "삭제",
                    containerColor = Color.Red
                )
            }
        )
    }
}
