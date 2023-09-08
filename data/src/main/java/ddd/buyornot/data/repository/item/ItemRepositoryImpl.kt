package ddd.buyornot.data.repository.item

import ddd.buyornot.data.source.item.ItemRemoteDataSource
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemRemoteDataSource: ItemRemoteDataSource
) : ItemRepository{

    override suspend fun fetchItem(url: String) = itemRemoteDataSource.fetchItem(url)
}