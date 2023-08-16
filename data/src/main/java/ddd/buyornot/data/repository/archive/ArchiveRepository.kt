package ddd.buyornot.data.repository.archive

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.archive.ArchiveDeleteResult
import ddd.buyornot.data.model.archive.ArchiveResponse
import ddd.buyornot.data.model.archive.DeleteArchiveReq

interface ArchiveRepository {

    suspend fun postArchiveItem(itemId: Int) : Result<BaseApiResponse<ArchiveResponse>>

    suspend fun patchArchiveItemLike(itemId: Int) : Result<BaseApiResponse<ArchiveResponse>>

    suspend fun patchArchiveItemDelete(deleteArchiveReq: DeleteArchiveReq) : Result<BaseApiResponse<ArchiveDeleteResult>>

    suspend fun fetchPostList(page: Int, count: Int) : Result<BaseApiResponse<List<ArchiveResponse>>>

    suspend fun fetchPostLikedList(page: Int, count: Int) : Result<BaseApiResponse<List<ArchiveResponse>>>
}