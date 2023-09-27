package ddd.buyornot.data.repository.archive

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.archive.ArchiveResponse
import ddd.buyornot.data.model.archive.DeleteArchiveReq

interface ArchiveRepository {

    suspend fun postArchiveItem(itemUrl: String) : BaseApiResponse<ArchiveResponse>?

    suspend fun postArchiveItem(id: Int) : BaseApiResponse<ArchiveResponse>?

    suspend fun patchArchiveItemLike(archiveId: Int) : BaseApiResponse<ArchiveResponse>?

    suspend fun patchArchiveItemDelete(deleteArchiveReq: DeleteArchiveReq) : BaseApiResponse<String>?

    suspend fun fetchArchiveList(page: Int, count: Int) : BaseApiResponse<List<ArchiveResponse>>?

    suspend fun fetchPostLikedList(page: Int, count: Int) : BaseApiResponse<List<ArchiveResponse>>?
}