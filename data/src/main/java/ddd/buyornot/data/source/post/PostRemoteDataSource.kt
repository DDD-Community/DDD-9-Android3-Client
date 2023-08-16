package ddd.buyornot.data.source.post

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.model.post.PostResponse

interface PostRemoteDataSource {

    suspend fun postNewPost(postRequest: PostRequest) : Result<BaseApiResponse<PostResponse>>

    suspend fun postNewPostFromArchive(postRequest: PostRequest, itemId1: Int, itemId2: Int) : Result<BaseApiResponse<PostResponse>>

    suspend fun patchPostModify(postRequest: PostRequest, postId: Int) : Result<BaseApiResponse<PostResponse>>

    suspend fun patchPostDelete(postId: Int) : Result<BaseApiResponse<Int>>

    suspend fun patchPostFinish(postId: Int) : Result<BaseApiResponse<PostResponse>>

    suspend fun fetchPost(postId: Int) : Result<BaseApiResponse<PostResponse>>

    suspend fun fetchTemporaryPost() : Result<BaseApiResponse<PostResponse>>

    suspend fun fetchPostList(page: Int, count: Int) : Result<BaseApiResponse<PostResponse>>

    suspend fun fetchOnGoingPostList(page: Int, count: Int) : Result<BaseApiResponse<PostResponse>>

    suspend fun fetchClosedPostList(page: Int, count: Int) : Result<BaseApiResponse<PostResponse>>
}