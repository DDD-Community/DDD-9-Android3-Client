package ddd.buyornot.data.service

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.model.post.PostResult
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface PostService {

    @POST("/api/post/new")
    suspend fun postNewPost(
        @Body postRequest: PostRequest
    ): BaseApiResponse<PostResult>

    @POST("/api/post/from-archive")
    suspend fun postNewPostFromArchive(
        @Body postRequest: PostRequest,
        @Query("itemId1") itemId1: Int,
        @Query("itemId2") itemId2: Int,
    ): BaseApiResponse<PostResult>

}
