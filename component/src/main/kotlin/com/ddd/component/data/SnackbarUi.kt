package com.ddd.component.data

import androidx.annotation.DrawableRes

// TODO: UiEvent sealed class로 개선
enum class SnackbarUi(val message: String? = null, @DrawableRes val icon: Int? = null) {
    NONE,
    DELETE_ITEM("아카이브함에서 상품을 삭제했어요"),
    POLL("투표했어요!"),
    POST_ARCHIVE("\uD83D\uDC96 아카이브함에 상품을 추가했어요")
}