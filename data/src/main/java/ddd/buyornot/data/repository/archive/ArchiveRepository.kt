package ddd.buyornot.data.repository.archive

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.archive.PostListResponse

interface ArchiveRepository {

    suspend fun fetchPostList(page: Int, count: Int) : Result<BaseApiResponse<PostListResponse>>

    suspend fun fetchPostLikedList(page: Int, count: Int) : Result<BaseApiResponse<PostListResponse>>
}