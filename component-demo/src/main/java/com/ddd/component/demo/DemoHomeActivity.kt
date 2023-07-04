package com.ddd.component.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ddd.component.BDSBottomSheetLayout
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSFontFamily
import com.ddd.component.theme.BuyOrNotTheme
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
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

@Composable
@ExperimentalMaterialApi
fun DemoHomeScreen(
    navController: NavHostController
) {
    DemoBottomSheet(navController)
}

@Composable
@ExperimentalMaterialApi
fun DemoBottomSheet(
    navController: NavHostController
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    BDSBottomSheetLayout(
        coroutineScope = coroutineScope,
        sheetState = sheetState,
        sheetContent = {
            Box(modifier = Modifier.fillMaxWidth().height(224.dp)) {
                BDSText(
                    text = "상품을 삭제할까요?",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = BDSFontFamily.English,
                    modifier = Modifier
                        .offset(y = 52.dp)
                        .align(Alignment.TopCenter)
                )
                BDSText(
                    text = "선택하신 상품을 정말 삭제하시겠어요?",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .offset(y = 86.dp)
                        .align(Alignment.TopCenter)
                )
                Row(
                    modifier = Modifier
                        .offset(y = 144.dp)
                        .align(Alignment.TopCenter)
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        BDSText(
                            text = "취소"
                        )
                    }
                    Button(onClick = { /*TODO*/ }) {
                        BDSText(
                            text = "삭제"
                        )
                    }
                }
            }
        },
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
                    coroutineScope.launch {
                        if (sheetState.isVisible) {
                            sheetState.hide()
                        } else {
                            sheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    }
                }) {
                Text(
                    text = "BottomSheet Test",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}