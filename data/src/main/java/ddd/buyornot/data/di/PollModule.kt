package ddd.buyornot.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ddd.buyornot.data.repository.poll.PollRepository
import ddd.buyornot.data.repository.poll.PollRepositoryImpl
import ddd.buyornot.data.service.PollService
import ddd.buyornot.data.source.poll.PollRemoteDataSource
import ddd.buyornot.data.source.poll.PollRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PollModule {

    @Provides
    @Singleton
    fun providePollRemoteDataSource(
        pollService: PollService,
    ): PollRemoteDataSource = PollRemoteDataSourceImpl(pollService)

    @Provides
    @Singleton
    fun providePollRepository(
        pollRemoteDataSource: PollRemoteDataSource
    ): PollRepository = PollRepositoryImpl(pollRemoteDataSource)
}