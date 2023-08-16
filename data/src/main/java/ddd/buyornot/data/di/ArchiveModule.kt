package ddd.buyornot.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ddd.buyornot.data.repository.archive.ArchiveRepository
import ddd.buyornot.data.repository.archive.ArchiveRepositoryImpl
import ddd.buyornot.data.service.ArchiveService
import ddd.buyornot.data.source.archive.ArchiveRemoteDataSource
import ddd.buyornot.data.source.archive.ArchiveRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArchiveModule {

    @Provides
    @Singleton
    fun bindArchiveRemoteDataSource(
        archiveService: ArchiveService,
    ): ArchiveRemoteDataSource = ArchiveRemoteDataSourceImpl(archiveService)

    @Provides
    @Singleton
    fun bindArchiveRepository(
        archiveRemoteDataSource: ArchiveRemoteDataSource,
    ): ArchiveRepository = ArchiveRepositoryImpl(archiveRemoteDataSource)
}