package com.ddd.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * BDSBottomSheet
 *
 * 화면 하단에 Composable Content를 노출하는 Sheet
 * sheetContent : BottomSheet 내부에 넣을 Composable Content
 * content : BottomSheet를 감싸는 Screen Composable Content
 */

@Composable
@ExperimentalMaterialApi
fun BDSBottomSheetLayout(
    coroutineScope: CoroutineScope,
    sheetState: ModalBottomSheetState,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    val roundedCornerShape = 16.dp

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetContent =  sheetContent,
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerShape, topEnd = roundedCornerShape),
        content = content
    )
}

