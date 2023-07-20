package com.ddd.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.holix.android.bottomsheetdialog.compose.BottomSheetBehaviorProperties
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties

/**
 * BDSBottomSheet
 * 화면 하단에 Composable Content를 노출하는 Sheet
 * 높이가 가변적으로 컨텐츠 길이에 따라 높이가 변동 가능
 * top margin 최소 72dp
 * 사용자가 높이를 직접 줄일 수 없음
 * modifier: 그냥 Modifier
 * onDismissRequest: 바텀 시트가 사라짐(hidden) 애니메이션이 끝난 후에 실행할 동작
 * sheetState: bottom sheet 상태, 보여지고 있는지 숨김 상태인지 전체 확장 상태인지 등
 * shape: bottom sheet 모양
 * containerColor: bottom sheet의 배경색
 * contentColor: bottom sheet content 기본 색상. containerColor에 대해 일치하는 content color 색상
 * 또는 containerColor가 테마의 색상이 아닌 경우 현재 LocalContentColor로 기본 설정
 * tonalElevation: bottom sheet 색조 elevation
 * scrimColor: bottom sheet가 열려있을 때 content를 가리는 scrim 색
 * dragHandle: bottom sheet 스와이프용 핸들
 * content: bottom sheet 내부에 그릴 Composable
 */

/*@Composable
@ExperimentalMaterial3Api
fun BDSBottomSheetLayout(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    sheetState: SheetState = rememberModalBottomSheetState(),
    shape: Shape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp
    ),
    containerColor: Color = BottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    tonalElevation: Dp = BottomSheetDefaults.Elevation,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    dragHandle: @Composable (() -> Unit)? = null,
    headerContent: @Composable (() -> Unit)? = null,
    bodyContent: @Composable (() -> Unit)? = null,
    buttonContent: @Composable (() -> Unit)? = null
) {
    ModalBottomSheet(
        modifier = Modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        scrimColor = scrimColor,
        dragHandle = dragHandle,
        content = {
            headerContent?.invoke()
            bodyContent?.invoke()
            buttonContent?.invoke()
        }
    )
}
}*/

@Composable
fun BDSBottomSheet(
    onDismissRequest: () -> Unit = { },
    properties: BottomSheetDialogProperties = BottomSheetDialogProperties(
        behaviorProperties = BottomSheetBehaviorProperties(
            state = BottomSheetBehaviorProperties.State.Expanded,
            isDraggable = false
        )
    ),
    headerContent: (() -> Unit)? = null,
    bodyContent: (() -> Unit)? = null,
    bottomContent: (() -> Unit)? = null
) {
    BottomSheetDialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
            )
        ) {
            headerContent?.invoke()
            bodyContent?.invoke()
            bottomContent?.invoke()
        }
    }
}

@Composable
fun BDSBottomSheetPostList(
    onDismissRequest: () -> Unit = { },
    properties: BottomSheetDialogProperties = BottomSheetDialogProperties(
        behaviorProperties = BottomSheetBehaviorProperties(
            state = BottomSheetBehaviorProperties.State.Expanded,
            isDraggable = false
        )
    ),
    headerContent: @Composable (() -> Unit)? = null,
    buttonContent: @Composable (() -> Unit)? = null,
    postItemList: List<PostItem>
) {
    BottomSheetDialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                // .padding(top = 72.dp)
                .fillMaxHeight(0.9f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    )
                ),
        ) {
            val context = LocalContext.current
            var bottomHeight by remember { mutableStateOf(0) }

            Column {
                headerContent?.invoke()
                LazyColumn(
                    modifier = Modifier.padding(bottom = bottomHeight.pxToDp())
                ) {
                    items(postItemList) { postItem ->
                        BDSPostCard(postItem = postItem)
                    }
                }
            }
            Box(
                modifier = Modifier
                    .onGloballyPositioned { layoutCoordinates ->
                        bottomHeight = layoutCoordinates.size.height
                    }
                    .align(Alignment.BottomStart)
            ) {
                buttonContent?.invoke()
            }
        }
    }
}

@Composable
fun BDSBottomSheetHeader(
    left: @Composable (() -> Unit)? = null,
    right: @Composable (() -> Unit)? = null,
    center: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        left?.invoke()
        center?.invoke()
        right?.invoke()
    }
}

@Composable
fun BDSBottomSheetSingleButton(
    button: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 36.dp)
    ) {
        button?.invoke()
    }
}

@Composable
fun BDSBottomSheetVerticalDualButton(
    confirmButton: @Composable (() -> Unit)? = null,
    cancelButton: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 36.dp)
    ) {
        confirmButton?.invoke()
        cancelButton?.invoke()
    }
}


@Composable
fun BDSBottomSheetHorizontalDualButton(
    confirmButton: @Composable (() -> Unit)? = null,
    cancelButton: @Composable (() -> Unit)? = null
) {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 36.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally)
        ) {
            confirmButton?.invoke()
            cancelButton?.invoke()
        }
    }
}