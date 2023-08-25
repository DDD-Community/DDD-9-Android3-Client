package ddd.buyornot.data.model.poll

import com.google.gson.annotations.SerializedName

data class PollResponse(
    @SerializedName("result")
    val result: PollResult? = null
)

data class PollResult(
    // 비추천
    @SerializedName("additionalProp1")
    val additionalProp1: Int? = null,

    // A 추천
    @SerializedName("additionalProp2")
    val additionalProp2: Int? = null,

    // B 추천
    @SerializedName("additionalProp3")
    val additionalProp3: Int? = null
)
