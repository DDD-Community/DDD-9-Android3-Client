package com.ddd.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BottomNavigationItem.Companion.bottomNavigationItems
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BDSColor.Gray800
import com.ddd.component.theme.BDSColor.Primary400
import com.ddd.component.theme.BDSColor.SlateGray500
import com.ddd.component.theme.BDSColor.SlateGray600

/**
 * @param selectedNavigationItem 선택된 탭
 * @param onClickNavigationItem 탭 클릭 시 호출되는 콜백
 * @param content 탭 내용
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BDSBottomNavigationLayout(
    modifier: Modifier = Modifier,
    selectedNavigationItem: BottomNavigationItem,
    onClickNavigationItem: (BottomNavigationItem) -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                shadowElevation = 8.dp,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectableGroup(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    bottomNavigationItems.forEach { item ->
                        val isSelected = selectedNavigationItem == item

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 14.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
                                .weight(1f)
                                .clickableWithoutRipple {
                                    onClickNavigationItem(item)
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            BDSImage(
                                modifier = Modifier
                                    .padding(bottom = 4.dp)
                                    .size(
                                        if (item is BottomNavigationItem.Add) 36.dp else 24.dp
                                    ),
                                resId = item.icon,
                                contentDescription = item.title,
                                tintColor = when {
                                    item is BottomNavigationItem.Add -> Gray800
                                    isSelected -> Primary400
                                    else -> SlateGray500
                                },
                            )
                            item.title?.let { text ->
                                BDSText(
                                    text = text,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) Primary400 else SlateGray600,
                                )
                            }
                        }
                    }
                }
            }
        }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            content()
        }
    }
}

sealed class BottomNavigationItem(
    val title: String? = null,
    @DrawableRes val icon: Int,
    val route: String
) {

    object Home : BottomNavigationItem("홈", R.drawable.ic_house, "home")
    object Add : BottomNavigationItem(null, R.drawable.ic_add_in_circle, "add")
    object Archive : BottomNavigationItem("아카이브", R.drawable.ic_archive_navigation, "archive")

    companion object {
        val bottomNavigationItems = listOf(
            Home,
            Add,
            Archive
        )
    }
}

@Composable
fun BDSEditBottomNavigationLayout(
    modifier: Modifier = Modifier,
    clickEnabled: Boolean = false,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shadowElevation = 8.dp,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectableGroup()
                        .background(color = Primary400),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    EditBottomNavigationItem.bottomNavigationItems.forEach { item ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 14.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
                                .weight(1f)
                                .clickableWithoutRipple(
                                    enabled = clickEnabled,
                                    onClick = { item.onClickNavigationEvent }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            BDSImage(
                                modifier = Modifier
                                    .padding(bottom = 4.dp)
                                    .size(24.dp),
                                resId = item.icon,
                                contentDescription = item.title,
                                tintColor = BDSColor.White,
                            )
                            BDSText(
                                text = item.title,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = BDSColor.White,
                            )
                        }
                    }
                }
            }
        }) { padding ->
        Box(
            modifier = Modifier.padding(padding)
        ) {
            content()
        }
    }
}

sealed class EditBottomNavigationItem(
    val title: String,
    @DrawableRes val icon: Int,
    val route: String,
    val onClickNavigationEvent: () -> Unit
) {
    object Write : EditBottomNavigationItem("글쓰기", R.drawable.ic_edit, "Write", { /* 글쓰기 화면 이동 */})
    object Delete : EditBottomNavigationItem("삭제", R.drawable.ic_delete,"Delete", { /* 삭제 Dialog 노출 */ })

    companion object {
        val bottomNavigationItems = listOf(
            Write,
            Delete
        )
    }
}