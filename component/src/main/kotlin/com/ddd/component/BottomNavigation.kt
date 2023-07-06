package com.ddd.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BottomNavigationItem.Companion.bottomNavigationItems
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
            NavigationBar(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 8.dp,
                containerColor = Color.White,
                contentColor = SlateGray500,
            ) {
                bottomNavigationItems.forEach { item ->
                    val isSelected = selectedItem == item

                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Primary400,
                            selectedTextColor = Primary400,
                            unselectedIconColor = SlateGray500,
                            unselectedTextColor = SlateGray600,
                            indicatorColor = Color.White,
                        ),
                        selected = isSelected,
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                            )
                        },
                        label = item.title?.let { text ->
                            {
                                Text(
                                    text = text,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        },
                        onClick = {
                            selectedItem = item
                            onClickTab(item)
                        }
                    )
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
    object Add : BottomNavigationItem(null, R.drawable.ic_add_archive, "add")
    object Archive : BottomNavigationItem("아카이브", R.drawable.ic_heart_mono_fill, "archive")

    companion object {
        val bottomNavigationItems = listOf(
            Home,
            Add,
            Archive
        )
    }
}
