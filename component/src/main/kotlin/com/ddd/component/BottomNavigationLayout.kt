package com.ddd.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
                                .padding(vertical = 8.dp)
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
                                        if (item is BottomNavigationItem.Add) 32.dp else 24.dp
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
        Box(
            modifier = Modifier.padding(padding)
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
    object Archive : BottomNavigationItem("아카이브", R.drawable.ic_heart_mono_fill, "archive")

    companion object {
        val bottomNavigationItems = listOf(
            Home,
            Add,
            Archive
        )
    }
}
