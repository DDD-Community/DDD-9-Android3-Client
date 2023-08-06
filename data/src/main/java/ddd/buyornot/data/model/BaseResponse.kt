package ddd.buyornot.data.model

import com.google.gson.annotations.SerializedName

open class BaseApiResponse<T> {

    @SerializedName("resultMsg")
    val resultMsg: String = ""

    @SerializedName("resultCode")
    val resultCode: Int = 0

    @SerializedName("result")
    val result: T? = null

    val isSuccess: Boolean
        get() = resultCode in 200..299

    val isFailure: Boolean
        get() = !isSuccess
}

inline fun <reified T> BaseApiResponse<T>.getOrNull(): T? {
    return if (isSuccess) {
        try {
            result as T
        } catch (e: ClassCastException) {
            null
        }
    } else {
        null
    }
}

inline fun <reified T> getOrDefault(
    block: () -> BaseApiResponse<T>,
    default: T? = null,
): Result<T?> {
    return runCatching {
        block().getOrNull() ?: default
    }
}