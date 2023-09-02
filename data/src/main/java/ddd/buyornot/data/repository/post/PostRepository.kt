package ddd.buyornot.data.repository.post

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.model.post.PostResult

interface PostRepository {

    suspend fun postPublishPost(postId: Int, postRequest: PostRequest): BaseApiResponse<PostResult>?

    suspend fun postNewVote(postRequest: PostRequest): BaseApiResponse<PostResult>?

    suspend fun postNewPostFromArchive(postRequest: PostRequest, itemId1: Int, itemId2: Int): BaseApiResponse<PostResult>?

    suspend fun patchPostModify(postRequest: PostRequest, postId: Int): BaseApiResponse<PostResult>?

    suspend fun patchPostDelete(postId: Int): BaseApiResponse<Int>?

    suspend fun patchPostFinish(postId: Int): BaseApiResponse<PostResult>?

    suspend fun fetchPost(postId: Int): BaseApiResponse<PostResult>?

    suspend fun fetchTemporaryPost(): BaseApiResponse<List<PostResult>>?

    suspend fun fetchPostList(page: Int, count: Int): BaseApiResponse<List<PostResult>>?

    suspend fun fetchOnGoingPostList(page: Int, count: Int): BaseApiResponse<List<PostResult>>?

    suspend fun fetchClosedPostList(page: Int, count: Int): BaseApiResponse<List<PostResult>>?
}