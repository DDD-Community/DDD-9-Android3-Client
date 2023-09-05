package ddd.buyornot.data.model.post

import com.google.gson.annotations.SerializedName

data class PostRequest(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("publicStatus")
    val publicStatus: String = PostResult.PublicStatus.PUBLIC.name,
    @SerializedName("itemUrls")
    val itemUrls: List<String>? = null,
    @SerializedName("published")
    val published: Boolean = true,
)
