package ddd.buyornot.data.source.archive

import ddd.buyornot.data.model.archive.DeleteArchiveReq
import ddd.buyornot.data.service.ArchiveService
import javax.inject.Inject

class ArchiveRemoteDataSourceImpl @Inject constructor(
    private val archiveService: ArchiveService,
) : ArchiveRemoteDataSource {

    override suspend fun postArchiveItem(itemUrl: String) = runCatching {
        archiveService.postArchiveItem(itemUrl)
    }.getOrNull()

    override suspend fun postArchiveItem(itemId: Int) = runCatching {
        archiveService.postArchiveItem(itemId)
    }.getOrNull()

    override suspend fun patchArchiveItemLike(archiveId: Int) = runCatching {
        archiveService.patchArchiveItemLike(archiveId)
    }.getOrNull()

    override suspend fun patchArchiveItemDelete(deleteArchiveReq: DeleteArchiveReq) = runCatching {
        archiveService.patchArchiveItemDelete(deleteArchiveReq)
    }.getOrNull()

    override suspend fun fetchPostList(page: Int, count: Int) = runCatching {
        archiveService.fetchArchivePostList(page, count)
    }.getOrNull()

    override suspend fun fetchLikedPostList(page: Int, count: Int) = runCatching {
        archiveService.fetchArchiveLikedPostList(page, count)
    }.getOrNull()
}