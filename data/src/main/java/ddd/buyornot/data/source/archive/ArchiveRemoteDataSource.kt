package ddd.buyornot.data.source.archive

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.archive.ArchiveResponse
import ddd.buyornot.data.model.archive.DeleteArchiveReq

interface ArchiveRemoteDataSource {

    suspend fun postArchiveItem(itemUrl: String) : Result<BaseApiResponse<ArchiveResponse>>

    suspend fun postArchiveItem(itemId: Int) : Result<BaseApiResponse<ArchiveResponse>>

    suspend fun patchArchiveItemLike(itemId: Int) : Result<BaseApiResponse<ArchiveResponse>>

    suspend fun patchArchiveItemDelete(deleteArchiveReq: DeleteArchiveReq) : Result<BaseApiResponse<Int>>

    suspend fun fetchPostList(page: Int, count: Int) : Result<BaseApiResponse<List<ArchiveResponse>>>

    suspend fun fetchLikedPostList(page: Int, count: Int) : Result<BaseApiResponse<List<ArchiveResponse>>>
}