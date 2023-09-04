package ddd.buyornot.data.source.archive

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.archive.ArchiveResponse
import ddd.buyornot.data.model.archive.DeleteArchiveReq

interface ArchiveRemoteDataSource {

    suspend fun postArchiveItem(itemUrl: String) : BaseApiResponse<ArchiveResponse>?

    suspend fun postArchiveItem(itemId: Int) : BaseApiResponse<ArchiveResponse>?

    suspend fun patchArchiveItemLike(archiveId: Int) : BaseApiResponse<ArchiveResponse>?

    suspend fun patchArchiveItemDelete(deleteArchiveReq: DeleteArchiveReq) : BaseApiResponse<Int>?

    suspend fun fetchPostList(page: Int, count: Int) : BaseApiResponse<List<ArchiveResponse>>?

    suspend fun fetchLikedPostList(page: Int, count: Int) : BaseApiResponse<List<ArchiveResponse>>?
}