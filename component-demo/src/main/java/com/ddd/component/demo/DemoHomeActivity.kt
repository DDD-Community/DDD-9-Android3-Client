package com.ddd.component.demo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ddd.component.BDSIconSnackbar
import com.ddd.component.theme.BuyOrNotTheme
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
class DemoHomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyOrNotTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    DemoNavHost(navController = navController)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@ExperimentalMaterial3Api
fun DemoHomeScreen(
    navController: NavHostController
) {
    val scrollState = rememberScrollState()
    var openDialog by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by remember { mutableStateOf(true) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState) {
                /*BDSSnackbar(
                    text = "내 글에 43명이 투표했어요!",
                    action = {
                        Button(
                            onClick = {}
                        ) {
                            BDSText("바로가기")
                        }
                    }
                )*/
                /*BDSSingleTextSnackbar(
                    text = "아카이브함에서 상품을 삭제했어요"
                )*/
                BDSIconSnackbar(
                    text = "클립보드의 상품 링크를 탐지했어요.",
                    icon = painterResource(id = com.ddd.component.R.drawable.ic_noti)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(DemoNavigationRoute.Typography.route)
                }) {
                Text(
                    text = "Typography Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    navController.navigate(DemoNavigationRoute.Image.route)
                }) {
                Text(
                    text = "Image Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    openDialog = !openDialog
                }) {
                Text(
                    text = "Dialog Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    navController.navigate(DemoNavigationRoute.Theme.route)
                }) {
                Text(
                    text = "Theme Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    navController.navigate(DemoNavigationRoute.Layout.route)
                }) {
                Text(
                    text = "Layout Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "BDS Snackbar",
                            duration = SnackbarDuration.Short
                        )
                    }
                }) {
                Text(
                    text = "Snackbar Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    navController.navigate(DemoNavigationRoute.ItemCard.route)
                }) {
                Text(
                    text = "ItemCard Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    navController.navigate(DemoNavigationRoute.PostCard.route)
                }) {
                Text(
                    text = "PostCard Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    navController.navigate(DemoNavigationRoute.ArchiveScreen.route)
                }) {
                Text(
                    text = "ArchiveScreen Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    navController.navigate(DemoNavigationRoute.ArchiveEditScreen.route)
                }) {
                Text(
                    text = "ArchiveEditScreen Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    navController.navigate(DemoNavigationRoute.ProfileScreen.route)
                }) {
                Text(
                    text = "ProfileScreen Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    navController.navigate(DemoNavigationRoute.Button.route)
                }) {
                Text(
                    text = "Button Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    navController.navigate(DemoNavigationRoute.TextField.route)
                }) {
                Text(
                    text = "TextField Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        if (openDialog) {
            /*WritePostPageSaveAlertBottomSheet(
                onDismissRequest = { openDialog = false },
                sheetState = bottomSheetState
            )*/
            WritePostPageDefaultBottomSheet(
                onDismissRequest = { openDialog = false }
            )
        }
    }
}
