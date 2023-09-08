package ddd.buyornot.data.repository.archive

import ddd.buyornot.data.model.archive.DeleteArchiveReq
import ddd.buyornot.data.source.archive.ArchiveRemoteDataSource
import javax.inject.Inject

class ArchiveRepositoryImpl @Inject constructor(
    private val archiveRemoteDataSource: ArchiveRemoteDataSource,
) : ArchiveRepository {

    override suspend fun postArchiveItem(itemUrl: String) = archiveRemoteDataSource.postArchiveItem(itemUrl)

    override suspend fun postArchiveItem(id: Int) = archiveRemoteDataSource.postArchiveItem(id)

    override suspend fun patchArchiveItemLike(archiveId: Int) = archiveRemoteDataSource.patchArchiveItemLike(archiveId)

    override suspend fun patchArchiveItemDelete(deleteArchiveReq: DeleteArchiveReq) = archiveRemoteDataSource.patchArchiveItemDelete(deleteArchiveReq)

    override suspend fun fetchPostList(page: Int, count: Int) = archiveRemoteDataSource.fetchPostList(page, count)

    override suspend fun fetchPostLikedList(page: Int, count: Int) = archiveRemoteDataSource.fetchLikedPostList(page, count)

    override suspend fun fetchItem(url: String) = archiveRemoteDataSource.fetchItem(url)
}