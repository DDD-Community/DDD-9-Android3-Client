package com.ddd.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.theme.BDSColor.Primary400
import java.text.DecimalFormat

// TODO: data class 위치 변경
data class ArchiveItem(
    val itemId: Int? = null,
    val imageUrl: String? = null,
    val brand: String? = null,
    val name: String? = null,
    val discount: Int? = null,
    val price: Int? = null,
    var liked: Boolean = false
) {
    val isSelected: MutableState<Boolean> = mutableStateOf(false)
}

@Composable
fun BDSArchiveItemCard(
    archiveItem: ArchiveItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isEditMode: Boolean = false,
    isSelected: Boolean = false,
    onClickSelect: () -> Unit = {},
    isLike: Boolean = false,
    onClickLike: () -> Unit = {}
) {
    Box(
        modifier
            .size(164.dp, 260.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .size(164.dp, 164.dp)
                    .clickable { onClick() }
            ) {
                BDSImage(
                    url = archiveItem.imageUrl,
                    modifier = Modifier
                        .fillMaxSize()
                        .then(modifier),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "image description",
                    tintColor = if (isEditMode && isSelected) Primary400.copy(0.4f) else null,
                    blendMode = if (isEditMode && isSelected) BlendMode.Screen else null
                )

                if (isEditMode) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 10.dp, end = 10.dp)
                    ) {
                        BDSCheckbox(
                            checkedImage = R.drawable.ic_check,
                            uncheckedImage = R.drawable.ic_uncheck,
                            checked = isSelected,
                            onClick = onClick
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 10.dp, end = 10.dp)
                    ) {
                        BDSCheckbox(
                            checkedImage = R.drawable.ic_heart_mono_fill,
                            uncheckedImage = R.drawable.ic_heart_mono_stroke,
                            checked = isLike,
                            onClick = onClickLike
                        )
                    }
                }
            }
            if (archiveItem.brand != null) {
                Spacer(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(12.dp)
                )
                BDSText(
                    text = archiveItem.brand,
                    style = MaterialTheme.typography.labelMedium,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            if (archiveItem.name != null) {
                Spacer(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(4.dp)
                )
                BDSText(
                    text = archiveItem.name,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodySmall,
                    lineHeight = 20.sp
                )
            }
            Spacer(
                modifier = modifier
                    .fillMaxWidth()
                    .height(2.dp)
            )
            Row {
                if (archiveItem.discount != null) {
                    BDSText(
                        text = "${archiveItem.discount}% ",
                        modifier = modifier.offset(0.dp, 2.dp),
                        color = Primary400,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 20.sp
                    )
                }
                if (archiveItem.price != null) {
                    BDSText(
                        text = archiveItem.price.toPriceFormat(),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

fun Number?.toPriceFormat() =
    if (this == null) {
        ""
    } else {
        val formatter = DecimalFormat("#,###원")
        formatter.format(this)
    }
