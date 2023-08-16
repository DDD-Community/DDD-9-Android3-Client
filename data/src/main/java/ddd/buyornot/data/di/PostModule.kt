package ddd.buyornot.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ddd.buyornot.data.repository.post.PostRepository
import ddd.buyornot.data.repository.post.PostRepositoryImpl
import ddd.buyornot.data.service.PostService
import ddd.buyornot.data.source.post.PostRemoteDataSource
import ddd.buyornot.data.source.post.PostRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostModule {

    @Provides
    @Singleton
    fun providePostRemoteDataSource(
        postService: PostService
    ): PostRemoteDataSource = PostRemoteDataSourceImpl(postService)

    @Provides
    @Singleton
    fun providePostRepository(
        postRemoteDataSource: PostRemoteDataSource
    ): PostRepository = PostRepositoryImpl(postRemoteDataSource)


}