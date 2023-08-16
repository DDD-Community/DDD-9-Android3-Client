package ddd.buyornot.data.source.archive

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.archive.PostListResponse

interface ArchiveRemoteDataSource {

    suspend fun fetchPostList(page: Int, count: Int) : Result<BaseApiResponse<PostListResponse>>

    suspend fun fetchLikedPostList(page: Int, count: Int) : Result<BaseApiResponse<PostListResponse>>
}