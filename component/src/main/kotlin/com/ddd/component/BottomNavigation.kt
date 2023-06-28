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
import androidx.compose.ui.unit.dp
import com.ddd.component.BottomNavigationItem.Companion.bottomNavigationItems
import com.ddd.component.theme.Grey500

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
                contentColor = Grey500,
            ) {
                bottomNavigationItems.forEach { item ->
                    NavigationBarItem(
                        selected = selectedItem == item,
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title
                            )
                        },
                        label = item.title?.let { text ->
                            { Text(text = text) }
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

    object Home : BottomNavigationItem("홈", android.R.drawable.ic_menu_manage, "home")
    object Add : BottomNavigationItem(null, android.R.drawable.ic_menu_add, "add")
    object Archive : BottomNavigationItem("아카이브", android.R.drawable.ic_menu_manage, "archive")

    companion object {
        val bottomNavigationItems = listOf(
            Home,
            Add,
            Archive
        )
    }
}
