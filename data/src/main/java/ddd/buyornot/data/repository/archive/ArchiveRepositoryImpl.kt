package ddd.buyornot.data.repository.archive

import ddd.buyornot.data.source.archive.ArchiveRemoteDataSource
import javax.inject.Inject

class ArchiveRepositoryImpl @Inject constructor(
    private val archiveRemoteDataSource: ArchiveRemoteDataSource,
) : ArchiveRepository {

    override suspend fun fetchPostList(page: Int, count: Int) = archiveRemoteDataSource.fetchPostList(page, count)

    override suspend fun fetchPostLikedList(page: Int, count: Int) = archiveRemoteDataSource.fetchLikedPostList(page, count)
}