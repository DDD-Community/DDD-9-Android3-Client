package com.ddd.component.demo

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSConfirmDialog
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSIconButton
import com.ddd.component.BDSImage
import com.ddd.component.BDSOutlinedButton
import com.ddd.component.BDSText
import com.ddd.component.clickableWithoutRipple
import com.ddd.component.theme.BDSColor.Red
import com.ddd.component.theme.BDSColor.SlateGray700
import com.ddd.component.theme.BDSColor.SlateGray800
import com.ddd.component.theme.BDSColor.SlateGray900
import com.ddd.component.theme.BDSColor.White
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen() {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    var showLogoutDialogState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(183.dp),
        ) {
            BDSImage(
                resId = R.drawable.bg_profile,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            BDSIconButton(
                modifier = Modifier.padding(16.dp),
                resId = com.ddd.component.R.drawable.ic_back,
                onClick = { },
                tint = White
            )
        }
        BDSText(
            text = "익명의티셔츠234",
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 42.dp, bottom = 34.dp),
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = SemiBold,
            color = SlateGray900
        )
        BDSText(
            text = "내 글 관리",
            modifier = Modifier.padding(start = 20.dp),
            fontSize = 12.sp,
            lineHeight = 14.sp,
            fontWeight = Bold,
            color = SlateGray700
        )
        Divider(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp))
        BodyView(
            modifier = Modifier.clickableWithoutRipple {  },
            left = {
                BDSText(
                    text = "내가 쓴 글",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = SemiBold,
                    color = SlateGray800
                )
            },
            right = {
                BDSImage(
                    resId = com.ddd.component.R.drawable.ic_arrow_right_small_mono,
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        Spacer(modifier = Modifier.height(42.dp))
        BDSText(
            text = "내 정보 관리",
            modifier = Modifier.padding(start = 20.dp),
            fontSize = 12.sp,
            lineHeight = 14.sp,
            fontWeight = Bold,
            color = SlateGray700
        )
        Divider(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp))
        BodyView(
            modifier = Modifier.clickableWithoutRipple { showLogoutDialogState = true },
            left = {
                BDSText(
                    text = "로그아웃",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = SemiBold,
                    color = SlateGray800
                )
            }
        )
        BodyView(
            modifier = Modifier.clickableWithoutRipple {  },
            left = {
                BDSText(
                    text = "계정삭제",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = SemiBold,
                    color = Red
                )
            }
        )
    }
    BDSImage(
        resId = com.ddd.component.R.drawable.ic_app_logo_sample,
        modifier = Modifier
            .offset(32.dp, 153.dp)
            .clip(CircleShape)
            .size(56.dp)
            .border(2.dp, White, CircleShape)
    )

    if (showLogoutDialogState) {
        val sheetState: SheetState = rememberModalBottomSheetState()
        BDSConfirmDialog(
            onDismissRequest = { showLogoutDialogState = false},
            title = "계정에서 로그아웃 할까요?",
            subTitle = "작성한 투표는 잘 보관하고 있을게요",
            sheetState = sheetState,
            cancelButton = {
                BDSOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showLogoutDialogState = false
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
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showLogoutDialogState = false
                                // 로그아웃
                            }
                        }
                    },
                    text = "로그아웃",
                    containerColor = Color.Red
                )
            }
        )
    }
}

@Composable
fun BodyView(
    modifier: Modifier = Modifier,
    left: @Composable (() -> Unit)? = null,
    right: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 13.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        left?.invoke()
        right?.invoke()
    }
}