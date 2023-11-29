package ddd.buyornot.data.source.item

import ddd.buyornot.data.service.ItemService
import javax.inject.Inject

class ItemRemoteDataSourceImpl @Inject constructor(
    private val itemService: ItemService
) : ItemRemoteDataSource {

    override suspend fun fetchItem(url: String) = runCatching {
        itemService.fetchItem(url)
    }.getOrNull()
}