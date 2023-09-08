package ddd.buyornot.data.repository.item

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.item.ItemResult

interface ItemRepository {

    suspend fun fetchItem(url: String) : BaseApiResponse<ItemResult>?
}