package ddd.buyornot.data.model.post

import com.google.gson.annotations.SerializedName
import ddd.buyornot.data.model.poll.PollResponse

data class PostResult(
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
    val pollStatus: PollStatus? = null,

    @SerializedName("publicStatus")
    val publicStatus: PublicStatus? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("userId")
    val userId: String? = null,

    @SerializedName("userNickname")
    val userNickname: String? = null
) {
    enum class PublicStatus {
        PUBLIC,             // 전체 공개
        PRIVATE,            // 제한된 공개
        TEMPORARY_STORAGE   // 임시 저장
    }

    enum class PollStatus {
        ONGOING,
        CLOSED
    }
}

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