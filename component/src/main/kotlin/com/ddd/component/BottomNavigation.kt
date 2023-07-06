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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BottomNavigationItem.Companion.bottomNavigationItems
import com.ddd.component.theme.Gray800
import com.ddd.component.theme.Primary400
import com.ddd.component.theme.SlateGray500
import com.ddd.component.theme.SlateGray600

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BDSBottomNavigation(
    onClickTab: (BottomNavigationItem) -> Unit,
    content: @Composable () -> Unit,
) {
    var selectedItem by remember { mutableStateOf(bottomNavigationItems.first()) }

    Scaffold(
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
                        val isSelected = selectedItem == item

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .weight(1f)
                                .clickableWithoutRipple {
                                    selectedItem = item
                                    onClickTab(item)
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(bottom = 4.dp)
                                    .size(
                                        if (item is BottomNavigationItem.Add) 32.dp else 24.dp
                                    ),
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                                tint = when {
                                    item is BottomNavigationItem.Add -> Gray800
                                    isSelected -> Primary400
                                    else -> SlateGray500
                                },
                            )
                            item.title?.let { text ->
                                Text(
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
