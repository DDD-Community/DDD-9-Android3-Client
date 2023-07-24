package com.ddd.component.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSBorderlessButton
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSIconButton
import com.ddd.component.BDSOutlinedButton

@Composable
fun ButtonTestScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        BDSFilledButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .width(92.dp)
                .height(54.dp), text = "Label"
        )
        BDSFilledButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .width(87.dp)
                .height(50.dp), text = "Label", contentPadding = BDSButtonInnerPadding.MEDIUM, fontSize = 16.sp
        )
        BDSFilledButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .width(74.dp)
                .height(34.dp), text = "Label", contentPadding = BDSButtonInnerPadding.SMALL, fontSize = 14.sp, lineHeight = 20.sp
        )
        BDSFilledButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .width(53.dp)
                .height(26.dp), text = "Label", contentPadding = BDSButtonInnerPadding.XSMALL, fontSize = 12.sp, lineHeight = 20.sp
        )
        BDSFilledButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .width(92.dp)
                .height(54.dp), text = "Label", enabled = false
        )
        BDSFilledButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .width(87.dp)
                .height(50.dp), text = "Label", contentPadding = BDSButtonInnerPadding.MEDIUM, fontSize = 16.sp, enabled = false
        )
        BDSFilledButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(74.dp)
                .height(34.dp),
            text = "Label",
            contentPadding = BDSButtonInnerPadding.SMALL,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            enabled = false
        )
        BDSFilledButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(53.dp)
                .height(26.dp),
            text = "Label",
            contentPadding = BDSButtonInnerPadding.XSMALL,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            enabled = false
        )

        BDSOutlinedButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .width(92.dp)
                .height(54.dp), text = "Label"
        )
        BDSOutlinedButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .width(87.dp)
                .height(50.dp), text = "Label", contentPadding = BDSButtonInnerPadding.MEDIUM, fontSize = 16.sp
        )

        BDSBorderlessButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(74.dp)
                .height(34.dp),
            text = "Label", contentPadding = BDSButtonInnerPadding.SMALL, fontSize = 14.sp, lineHeight = 20.sp,
        )
        BDSBorderlessButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .width(53.dp)
                .height(26.dp), text = "Label", contentPadding = BDSButtonInnerPadding.XSMALL, fontSize = 12.sp, lineHeight = 20.sp
        )
        BDSBorderlessButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(74.dp)
                .height(34.dp),
            text = "Label",
            contentPadding = BDSButtonInnerPadding.SMALL,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            enabled = false
        )
        BDSBorderlessButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(53.dp)
                .height(26.dp),
            text = "Label",
            contentPadding = BDSButtonInnerPadding.XSMALL,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            enabled = false
        )

        Row {
            BDSIconButton(painter = painterResource(id = com.ddd.component.R.drawable.ic_back), onClick = { /*TODO*/ })
            BDSIconButton(painter = painterResource(id = com.ddd.component.R.drawable.ic_arrow_right_small_mono), onClick = { /*TODO*/ })
            BDSIconButton(painter = painterResource(id = com.ddd.component.R.drawable.ic_add_in_circle), onClick = { /*TODO*/ })
            BDSIconButton(painter = painterResource(id = com.ddd.component.R.drawable.ic_close), onClick = { /*TODO*/ })
            BDSIconButton(painter = painterResource(id = com.ddd.component.R.drawable.ic_ic_round_close), onClick = { /*TODO*/ })
        }
    }
}