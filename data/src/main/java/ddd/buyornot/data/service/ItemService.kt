package ddd.buyornot.data.service

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.item.ItemResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemService {

    @GET("/api/item")
    suspend fun fetchItem(
        @Query("url") url: String
    ): BaseApiResponse<ItemResult>
}