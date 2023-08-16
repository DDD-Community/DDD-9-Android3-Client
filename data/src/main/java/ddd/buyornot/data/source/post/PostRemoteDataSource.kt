package ddd.buyornot.data.source.post

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.model.post.PostResult

interface PostRemoteDataSource {

    suspend fun postNewPost(postRequest: PostRequest) : Result<BaseApiResponse<PostResult>>

    suspend fun postNewPostFromArchive(postRequest: PostRequest, itemId1: Int, itemId2: Int) : Result<BaseApiResponse<PostResult>>

    suspend fun fetchPost(postId: Int) : Result<BaseApiResponse<PostResult>>

    suspend fun fetchTemporaryPost() : Result<BaseApiResponse<PostResult>>

    suspend fun fetchPostList(page: Int, count: Int) : Result<BaseApiResponse<PostResult>>

    suspend fun fetchOnGoingPostList(page: Int, count: Int) : Result<BaseApiResponse<PostResult>>

    suspend fun fetchClosedPostList(page: Int, count: Int) : Result<BaseApiResponse<PostResult>>
}