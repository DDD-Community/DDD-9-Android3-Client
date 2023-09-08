package ddd.buyornot.data.source.item

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.item.ItemResult

interface ItemRemoteDataSource {

    suspend fun fetchItem(url: String) : BaseApiResponse<ItemResult>?
}