package ddd.buyornot.data.repository.archive

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.archive.ArchiveResponse
import ddd.buyornot.data.model.archive.DeleteArchiveReq

interface ArchiveRepository {

    suspend fun postArchiveItem(itemUrl: String) : BaseApiResponse<ArchiveResponse>?

    suspend fun postArchiveItem(itemId: Int) : BaseApiResponse<ArchiveResponse>?

    suspend fun patchArchiveItemLike(itemId: Int) : BaseApiResponse<ArchiveResponse>?

    suspend fun patchArchiveItemDelete(deleteArchiveReq: DeleteArchiveReq) : BaseApiResponse<Int>?

    suspend fun fetchPostList(page: Int, count: Int) : BaseApiResponse<List<ArchiveResponse>>?

    suspend fun fetchPostLikedList(page: Int, count: Int) : BaseApiResponse<List<ArchiveResponse>>?
}