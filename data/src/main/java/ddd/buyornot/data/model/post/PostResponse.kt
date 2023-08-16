package ddd.buyornot.data.model.post

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("content")
    val content: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("participateStatus")
    val participateStatus: Boolean = false,

    @SerializedName("pollItemResponseList")
    val pollItemResponseList: List<PollItemResponse>? = null,

    @SerializedName("pollResponse")
    val pollResponse: PollResponse? = null,

    @SerializedName("pollStatus")
    val pollStatus: String? = null,

    @SerializedName("publicStatus")
    val publicStatus: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("userId")
    val userId: String? = null,

    @SerializedName("userNickname")
    val userNickname: String? = null
)

data class PollItemResponse(
    @SerializedName("brand")
    val brand: String? = null,

    @SerializedName("discountedPrice")
    val discountedPrice: Int? = null,

    @SerializedName("discountedRate")
    val discountedRate: Int? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("imgUrl")
    val imgUrl: String? = null,

    @SerializedName("itemId")
    val itemId: Int? = null,

    @SerializedName("itemName")
    val itemName: String? = null,

    @SerializedName("itemUrl")
    val itemUrl: String? = null,

    @SerializedName("originalPrice")
    val originalPrice: Int? = null
)

data class PollResponse(
    @SerializedName("result")
    val result: PollResult? = null
)

data class PollResult(
    @SerializedName("additionalProp1")
    val additionalProp1: Int? = null,
    @SerializedName("additionalProp2")
    val additionalProp2: Int? = null,
    @SerializedName("additionalProp3")
    val additionalProp3: Int? = null
)
