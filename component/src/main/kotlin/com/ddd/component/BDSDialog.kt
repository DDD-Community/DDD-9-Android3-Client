package com.ddd.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.theme.BDSColor.SlateGray600
import com.ddd.component.theme.BDSColor.SlateGray900

/**
 * BDSDialog
 *
 * 화면 하단에 Composable Content를 노출하는 Sheet
 * 높이는 고정값이며 Confirm과 Alert 두 가지 케이스에서 사용
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

@Composable
@ExperimentalMaterial3Api
fun BDSConfirmDialog(
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
    title: String,
    subTitle: String,
    cancelButton: @Composable (() -> Unit)? = null,
    acceptButton: @Composable (() -> Unit)? = null,
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        scrimColor = scrimColor,
        dragHandle = dragHandle,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(248.dp)
        ) {
            Spacer(modifier = Modifier.height(52.dp))
            BDSText(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = SlateGray900,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            BDSText(
                text = subTitle,
                modifier = Modifier.fillMaxWidth(),
                color = SlateGray600,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = Normal,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(34.dp))
            BDSBottomSheetHorizontalDualButton(
                cancelButton = cancelButton,
                acceptButton = acceptButton
            )
        }
    }
}

@Composable
@ExperimentalMaterial3Api
fun BDSAlertDialog(
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
    title: String,
    subTitle: String,
    alert: String,
    onClickAlert: () -> Unit,
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        scrimColor = scrimColor,
        dragHandle = dragHandle,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(248.dp)
        ) {
            Spacer(modifier = Modifier.height(52.dp))
            BDSText(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = SlateGray900,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            BDSText(
                text = subTitle,
                modifier = Modifier.fillMaxWidth(),
                color = SlateGray600,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = Normal,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(34.dp))
            Button(
                onClick = onClickAlert,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    )
            ) {
                BDSText(
                    text = alert,
                )
            }
        }
    }
}