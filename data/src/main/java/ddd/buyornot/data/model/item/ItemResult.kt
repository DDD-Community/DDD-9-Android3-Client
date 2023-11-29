package ddd.buyornot.data.model.item

import com.google.gson.annotations.SerializedName

data class ItemResult(
    @SerializedName("brand")
    val brand: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("imageUrl")
    val imageUrl: String?,

    @SerializedName("itemUrl")
    val itemUrl: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("updatedAt")
    val updatedAt: String?
)