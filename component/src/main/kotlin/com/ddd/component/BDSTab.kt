package com.ddd.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BDSColor.Gray200
import com.ddd.component.theme.BDSColor.Gray600
import com.ddd.component.theme.BDSColor.Gray900

/**
 * @param titles 탭 이름
 * @param selectedTabIndex 선택된 탭 인덱스
 * @param onTabSelected 탭 선택 시 호출되는 콜백
 */
@ExperimentalMaterial3Api
@Composable
fun BDSTab(
    modifier: Modifier = Modifier,
    titles: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .fillMaxWidth()
            .height(49.dp)
            .then(modifier),
        divider = {
            BDSDivider(color = Gray200)
        },
        containerColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = BDSColor.Primary500,
            )
        }
    ) {
        titles.forEachIndexed { index, text ->
            val selected = selectedTabIndex == index
            Tab(
                selected = selected,
                onClick = {
                    onTabSelected(index)
                },
                text = {
                    BDSText(
                        text = text,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = if (selected) Gray900 else Gray600,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            )
        }
    }
}