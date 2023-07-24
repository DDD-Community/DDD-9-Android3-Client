package com.ddd.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.theme.BDSColor.Black
import com.ddd.component.theme.BDSColor.Gray950
import com.ddd.component.theme.BDSColor.Primary400
import com.ddd.component.theme.BDSColor.SlateGray500
import com.ddd.component.theme.BDSColor.White
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun BDSButton(onClick: () -> Unit) {
    Button(

        onClick = onClick
    ) {

    }
}

@Composable
fun BDSButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape,
    border: BorderStroke?,
    containerColor: Color,
    contentColor: Color,
    disabledContainerColor: Color,
    disabledContentColor: Color,
    contentPadding: PaddingValues,
    text: String,
    fontSize: TextUnit,
    lineHeight: TextUnit,
    fontWeight: FontWeight,
    enabled: Boolean = true,
    withoutRipple: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    Button(
        onClick = onClick,
        modifier = modifier,
        contentPadding = contentPadding,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        border = border,
        enabled = enabled,
        interactionSource = if (withoutRipple) NoRippleInteractionSource() else interactionSource
    ) {
        BDSText(
            text = text,
            fontSize = fontSize,
            lineHeight = lineHeight,
            fontWeight = fontWeight
        )
    }
}

@Composable
fun BDSFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    containerColor: Color = Primary400,
    contentColor: Color = White,
    disabledContainerColor: Color = SlateGray500,
    disabledContentColor: Color = White,
    contentPadding: PaddingValues = BDSButtonInnerPadding.LARGE,
    text: String,
    fontSize: TextUnit = 18.sp,
    lineHeight: TextUnit = 24.sp,
    fontWeight: FontWeight = SemiBold,
    enabled: Boolean = true,
    withoutRipple: Boolean = false
) {
    BDSButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        border = null,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        contentPadding = contentPadding,
        text = text,
        fontSize = fontSize,
        lineHeight = lineHeight,
        fontWeight = fontWeight,
        enabled = enabled,
        withoutRipple = withoutRipple
    )
}

@Composable
fun BDSOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    containerColor: Color = White,
    contentColor: Color = Gray950,
    disabledContainerColor: Color = SlateGray500,
    disabledContentColor: Color = SlateGray500,
    contentPadding: PaddingValues = BDSButtonInnerPadding.LARGE,
    text: String,
    fontSize: TextUnit = 18.sp,
    lineHeight: TextUnit = 24.sp,
    fontWeight: FontWeight = SemiBold,
    enabled: Boolean = true,
    withoutRipple: Boolean = false
) {
    BDSButton(
        onClick = onClick,
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = SlateGray500),
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        contentPadding = contentPadding,
        text = text,
        fontSize = fontSize,
        lineHeight = lineHeight,
        fontWeight = fontWeight,
        enabled = enabled,
        withoutRipple = withoutRipple
    )
}

@Composable
fun BDSBorderlessButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    containerColor: Color = Color.Transparent,
    contentColor: Color = Black,
    disabledContainerColor: Color = Color.Transparent,
    disabledContentColor: Color = SlateGray500,
    contentPadding: PaddingValues = BDSButtonInnerPadding.LARGE,
    text: String,
    fontSize: TextUnit = 18.sp,
    lineHeight: TextUnit = 24.sp,
    fontWeight: FontWeight = SemiBold,
    enabled: Boolean = true,
    withoutRipple: Boolean = true
) {
    BDSButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        border = null,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        contentPadding = contentPadding,
        text = text,
        fontSize = fontSize,
        lineHeight = lineHeight,
        fontWeight = fontWeight,
        enabled = enabled,
        withoutRipple = withoutRipple
    )
}

object BDSButtonInnerPadding {
    val LARGE: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 15.dp)
    val MEDIUM: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 13.dp)
    val SMALL: PaddingValues = PaddingValues(horizontal = 20.dp, vertical = 7.dp)
    val XSMALL: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 3.dp)
}

class NoRippleInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}