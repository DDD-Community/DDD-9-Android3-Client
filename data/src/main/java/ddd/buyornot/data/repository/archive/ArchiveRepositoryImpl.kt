package ddd.buyornot.data.repository.archive

import ddd.buyornot.data.model.archive.DeleteArchiveReq
import ddd.buyornot.data.source.archive.ArchiveRemoteDataSource
import javax.inject.Inject

class ArchiveRepositoryImpl @Inject constructor(
    private val archiveRemoteDataSource: ArchiveRemoteDataSource,
) : ArchiveRepository {

    override suspend fun postArchiveItem(itemId: Int) = archiveRemoteDataSource.postArchiveItem(itemId)

    override suspend fun patchArchiveItemLike(itemId: Int) = archiveRemoteDataSource.patchArchiveItemLike(itemId)

    override suspend fun patchArchiveItemDelete(deleteArchiveReq: DeleteArchiveReq) = archiveRemoteDataSource.patchArchiveItemDelete(deleteArchiveReq)

    override suspend fun fetchPostList(page: Int, count: Int) = archiveRemoteDataSource.fetchPostList(page, count)

    override suspend fun fetchPostLikedList(page: Int, count: Int) = archiveRemoteDataSource.fetchLikedPostList(page, count)
}