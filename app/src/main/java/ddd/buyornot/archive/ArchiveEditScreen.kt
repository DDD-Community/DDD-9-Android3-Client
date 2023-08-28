package ddd.buyornot.archive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.ddd.component.BDSIconButton
import com.ddd.component.BDSImage
import com.ddd.component.BDSOutlinedButton
import com.ddd.component.BDSSingleTextSnackbar
import com.ddd.component.BDSText
import com.ddd.component.R
import com.ddd.component.theme.BDSColor
import ddd.buyornot.archive.viewmodel.ArchiveViewModel
import ddd.buyornot.findActivity
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun ArchiveEditScreen(
    viewModel: ArchiveViewModel
) {
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
    var showDeleteDialogState by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    BDSEditBottomNavigationLayout(
        selectCount = selectItems.size,
        onClickWrite = {},
        onClickDelete = { showDeleteDialogState = true }
    ) {
        Scaffold(
            topBar = {
                BDSAppBar(
                    left = {
                        BDSIconButton(resId = R.drawable.ic_back, onClick = { context.findActivity().finish() })
                    },
                    /*right = {
                        BDSFilledButton(
                            onClick = { context.findActivity().finish() },
                            text = "완료",
                            contentPadding = BDSButtonInnerPadding.SMALL,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                        )
                    },*/
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
            },
            snackbarHost = {
                SnackbarHost(snackbarHostState) {
                    BDSSingleTextSnackbar(text = "아카이브함에서 상품을 삭제했어요")
                }
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
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
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))

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
                            archiveItem.run {
                                BDSArchiveItemCard(
                                    archiveItem = this,
                                    onClick = {
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
    }

    if (showDeleteDialogState) {
        val sheetState: SheetState = rememberModalBottomSheetState()
        BDSConfirmDialog(
            onDismissRequest = { showDeleteDialogState = false },
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
                                showDeleteDialogState = false
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
                                showDeleteDialogState = false
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Delete Archive Items",
                                        duration = SnackbarDuration.Short
                                    )
                                }
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
