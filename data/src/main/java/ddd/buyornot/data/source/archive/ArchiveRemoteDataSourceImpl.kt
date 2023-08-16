package ddd.buyornot.data.source.archive

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.archive.ArchiveResponse
import ddd.buyornot.data.model.archive.DeleteArchiveReq
import ddd.buyornot.data.service.ArchiveService
import javax.inject.Inject

class ArchiveRemoteDataSourceImpl @Inject constructor(
    private val archiveService: ArchiveService,
) : ArchiveRemoteDataSource {

    override suspend fun postArchiveItem(itemId: Int) = runCatching {
        archiveService.postArchiveItem(itemId)
    }

    override suspend fun patchArchiveItemLike(itemId: Int) = runCatching {
        archiveService.patchArchiveItemLike(itemId)
    }

    override suspend fun patchArchiveItemDelete(deleteArchiveReq: DeleteArchiveReq) = runCatching {
        archiveService.patchArchiveItemDelete(deleteArchiveReq)
    }

    override suspend fun fetchPostList(page: Int, count: Int): Result<BaseApiResponse<ArchiveResponse>> = runCatching {
        archiveService.fetchArchivePostList(page, count)
    }

    override suspend fun fetchLikedPostList(page: Int, count: Int): Result<BaseApiResponse<ArchiveResponse>> = runCatching {
        archiveService.fetchArchiveLikedPostList(page, count)
    }
}