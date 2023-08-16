package ddd.buyornot.data.model.post

import com.google.gson.annotations.SerializedName

data class PostRequest(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("publicStatus")
    val publicStatus: String? = null,
    @SerializedName("itemUrls")
    val itemUrls: List<String>? = null
)
