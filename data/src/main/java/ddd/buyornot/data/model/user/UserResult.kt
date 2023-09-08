package ddd.buyornot.data.model.user

import com.google.gson.annotations.SerializedName

data class UserResult(
    @SerializedName("email")
    val email: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("nickname")
    val nickname: String?,

    @SerializedName("profile")
    val profile: String?
)