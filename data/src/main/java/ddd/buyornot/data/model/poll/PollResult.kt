package ddd.buyornot.data.model.poll

import com.google.gson.annotations.SerializedName

data class PollResponse(
    // 비추천
    @SerializedName("unrecommended")
    val unrecommended: Int = 0,
    // A 추천
    @SerializedName("firstItem")
    val firstItem: Int = 0,

    // B 추천
    @SerializedName("secondItem")
    val secondItem: Int = 0,
)