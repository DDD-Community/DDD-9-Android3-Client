package com.ddd.component.demo

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.component.AppBarUpButton
import com.ddd.component.BDSAppBar
import com.ddd.component.BDSBottomNavigationLayout
import com.ddd.component.BDSTab
import com.ddd.component.BottomNavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun LayoutTestScreen() {
    val activity = LocalContext.current as? Activity
    var selectedTabIndex by remember { mutableStateOf(0) }
    var selectedBottomNavigation: BottomNavigationItem by remember { mutableStateOf(BottomNavigationItem.bottomNavigationItems.first()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                BDSAppBar(
                    title = "화면 제목",
                    left = {
                        AppBarUpButton {
                            activity?.finish()
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                BDSTab(
                    titles = listOf("내 아카이브함", "링크 붙여넣기"),
                    selectedTabIndex = selectedTabIndex,
                ) {
                    selectedTabIndex = it
                }

                BDSBottomNavigationLayout(
                    selectedNavigationItem = selectedBottomNavigation,
                    onClickNavigationItem = {
                        selectedBottomNavigation = it
                    }
                ) {

                }
            }
        }
    }
}