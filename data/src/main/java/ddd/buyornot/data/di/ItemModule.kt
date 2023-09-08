package ddd.buyornot.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ddd.buyornot.data.repository.item.ItemRepository
import ddd.buyornot.data.repository.item.ItemRepositoryImpl
import ddd.buyornot.data.service.ItemService
import ddd.buyornot.data.source.item.ItemRemoteDataSource
import ddd.buyornot.data.source.item.ItemRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ItemModule {

    @Provides
    @Singleton
    fun provideRemoteDatasource(
        itemService: ItemService
    ): ItemRemoteDataSource = ItemRemoteDataSourceImpl(itemService)

    @Provides
    @Singleton
    fun provideItemRepository(
        itemRemoteDataSource: ItemRemoteDataSource
    ): ItemRepository = ItemRepositoryImpl(itemRemoteDataSource)
}