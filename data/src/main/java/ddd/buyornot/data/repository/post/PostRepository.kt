package ddd.buyornot.data.repository.post

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.model.post.PostResult

interface PostRepository {

    suspend fun postNewPost(postRequest: PostRequest) : Result<BaseApiResponse<PostResult>>

    suspend fun postNewPostFromArchive(postRequest: PostRequest, itemId1: Int, itemId2: Int) : Result<BaseApiResponse<PostResult>>
}