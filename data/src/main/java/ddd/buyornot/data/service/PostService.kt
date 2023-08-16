package ddd.buyornot.data.service

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.model.post.PostResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {

    @POST("/api/post/new")
    suspend fun postNewPost(
        @Body postRequest: PostRequest
    ): BaseApiResponse<PostResponse>

    @POST("/api/post/from-archive")
    suspend fun postNewPostFromArchive(
        @Body postRequest: PostRequest,
        @Query("itemId1") itemId1: Int,
        @Query("itemId2") itemId2: Int,
    ): BaseApiResponse<PostResponse>

    @PATCH("/api/post/{postId}/modification")
    suspend fun patchPostModify(
        @Body postRequest: PostRequest,
        @Path("postId") postId: Int,
    ): BaseApiResponse<PostResponse>

    @PATCH("/api/post/{postId}/deletion")
    suspend fun patchPostDelete(
        @Path("postId") postId: Int
    ): BaseApiResponse<Int>

    @PATCH("/api/post/{/postId}/end-poll")
    suspend fun patchPostFinish(
        @Path("postId") postId: Int
    ): BaseApiResponse<PostResponse>

    @GET("/api/post/{postId}")
    suspend fun fetchPost(
        @Path("postId") postId: Int,
    ): BaseApiResponse<PostResponse>

    @GET("/api/post/temporary")
    suspend fun fetchTemporaryPost() : BaseApiResponse<List<PostResponse>>

    @GET("/api/post/received")
    suspend fun fetchPostList(
        @Query("page") page: Int,
        @Query("count") count: Int
    ): BaseApiResponse<List<PostResponse>>

    @GET("/api/post/ongoing-list")
    suspend fun fetchOnGoingPostList(
        @Query("page") page: Int,
        @Query("count") count: Int
    ): BaseApiResponse<List<PostResponse>>

    @GET("/api/post/closed-list")
    suspend fun fetchClosedPostList(
        @Query("page") page: Int,
        @Query("count") count: Int
    ): BaseApiResponse<List<PostResponse>>
}
