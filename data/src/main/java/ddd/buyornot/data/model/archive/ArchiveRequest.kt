package ddd.buyornot.data.model.archive

import com.google.gson.annotations.SerializedName

data class DeleteArchiveReq(
    @SerializedName("ids")
    val ids: List<Int>?
)