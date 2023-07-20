package com.ddd.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
 * [BottomSheetDialog github](https://github.com/holixfactory/bottomsheetdialog-compose)
 * BDSBottomSheet
 * 화면 하단에 Composable Content를 노출하는 Sheet
 * 높이가 가변적으로 컨텐츠 길이에 따라 높이가 변동 가능
 * 최대 높이는 전체 화면의 90%
 * 사용자가 높이를 직접 줄일 수 없음
 * onDismissRequest: 바텀 시트가 사라짐(hidden, collapse) 애니메이션이 끝난 후에 실행할 동작
 * properties: 바텀 시트가 가질 수 있는 속성을 변경할 수 있음 (expand 상태, draggable 등)
 * headerContent: 바텀 시트 상단에 추가될 컴포저블 함수로 주로 BDSBottomSheetHeader를 사용
 * bodyContent: 바텀 시트 중앙에 추가될 컴포저블 함수
 * bottomContent: 바텀 시트 하단에 추가될 컴포저블 함수로 주로 BDSBottomSheet*Button 시리즈를 사용
 */

@Composable
fun BDSBottomSheet(
    onDismissRequest: () -> Unit = { },
    properties: BottomSheetDialogProperties = BottomSheetDialogProperties(
        behaviorProperties = BottomSheetBehaviorProperties(
            state = BottomSheetBehaviorProperties.State.Expanded,
            isDraggable = false
        )
    ),
    headerContent: @Composable (() -> Unit)? = null,
    bodyContent: @Composable (() -> Unit)? = null,
    bottomContent: @Composable (() -> Unit)? = null
) {
    /*
        이유는 모르겠으나 얘는 wrapContentHeight와 최대 높이 지정이 안됨. 매우 유감
     */
    BottomSheetDialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxHeight(0.9f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    )
                )
        ) {
            val context = LocalContext.current
            var bottomHeight by remember { mutableStateOf(0) }

            Column {
                headerContent?.invoke()
                Box(
                    modifier = Modifier
                        .padding(bottom = bottomHeight.pxToDp())
                ) {
                    bodyContent?.invoke()
                }
            }
            Box(
                modifier = Modifier
                    .onGloballyPositioned { layoutCoordinates ->
                        bottomHeight = layoutCoordinates.size.height
                    }
                    .align(Alignment.BottomStart)
            ) {
                bottomContent?.invoke()
            }
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