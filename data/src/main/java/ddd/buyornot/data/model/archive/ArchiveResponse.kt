package ddd.buyornot.data.model.archive

import com.google.gson.annotations.SerializedName

data class ArchiveResponse(
    @SerializedName("brand")
    val brand: String? = null,

    @SerializedName("discountedPrice")
    val discountedPrice: Int? = null,

    @SerializedName("discountedRate")
    val discountedRate: Int? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName(value = "imgUrl", alternate = ["imageUrl"])
    val imgUrl: String? = null,

    @SerializedName("itemId")
    val itemId: Int? = null,

    @SerializedName("itemName")
    val itemName: String? = null,

    @SerializedName("itemUrl")
    val itemUrl: String? = null,

    @SerializedName("liked")
    val liked: Boolean = false,

    @SerializedName("originalPrice")
    val originalPrice: Int? = null,

    @SerializedName("updatedAt")
    val updatedAt: String? = null,

    @SerializedName("userId")
    val userId: String? = null,
)