package com.ddd.component

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ddd.component.theme.BDSFontFamily

/*
파라미터 모호함 이슈로 인해 필요시에만 사용하는 것으로!
@Composable
fun BDSText(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: Dp = Dp.Unspecified,
    fontStyle: FontStyle? = FontStyle.Normal,
    fontWeight: FontWeight? = FontWeight.Normal,
    fontFamily: BDSFontFamily? = BDSFontFamily.Korean,
    letterSpacing: TextUnit = (-0.3).sp,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = TextAlign.Start,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    BDSText(
        text = buildAnnotatedString { append(text ?: "") },
        modifier = modifier,
        color = color,
        fontSize = dpToSp(dp = fontSize),
        fontStyle = fontStyle ?: FontStyle.Normal,
        fontWeight = fontWeight ?: FontWeight.Normal,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        inlineContent = inlineContent,
        onTextLayout = onTextLayout,
        style = style
    )
}
*/

@Composable
fun BDSText(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = FontStyle.Normal,
    fontWeight: FontWeight? = FontWeight.Normal,
    fontFamily: BDSFontFamily? = BDSFontFamily.Korean,
    letterSpacing: TextUnit = (-0.3).sp,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = TextAlign.Start,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = 1,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
) {
    BDSText(
        text = buildAnnotatedString { append(text ?: "") },
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle ?: FontStyle.Normal,
        fontWeight = fontWeight ?: FontWeight.Normal,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        inlineContent = inlineContent,
        onTextLayout = onTextLayout,
        style = style
    )
}

@Composable
fun BDSText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = FontStyle.Normal,
    fontWeight: FontWeight? = FontWeight.Normal,
    fontFamily: BDSFontFamily? = BDSFontFamily.Korean,
    letterSpacing: TextUnit = (-0.3).sp,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = TextAlign.Start,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = 1,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
) {
    var modifiedFontSize by remember { mutableStateOf(fontSize) }
    var readyToDraw by remember { mutableStateOf(false) }

    Text(
        text = text,
        modifier = modifier.drawWithContent {
            if (readyToDraw) drawContent()
        },
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle ?: FontStyle.Normal,
        fontWeight = fontWeight ?: FontWeight.Normal,
        fontFamily = fontFamily?.fontFamily ?: BDSFontFamily.Korean.fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        inlineContent = inlineContent,
        onTextLayout = onTextLayout ?: { textLayoutResult ->
            if (textLayoutResult.didOverflowWidth) {
                modifiedFontSize *= 0.9
            } else {
                readyToDraw = true
            }
        },
        style = style
    )
}

@Composable
private fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }