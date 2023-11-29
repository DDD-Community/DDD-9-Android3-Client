package ddd.buyornot.data.service

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.model.post.PostResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {

    // 임시저장 글 출간
    @POST("/api/post/publish-post/{postId}")
    suspend fun postPublishPost(
        @Path("postId") postId: Int,
        @Body postRequest: PostRequest
    ): BaseApiResponse<PostResult>

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

    @PATCH("/api/post/{postId}/modification")
    suspend fun patchPostModify(
        @Body postRequest: PostRequest,
        @Path("postId") postId: Int,
    ): BaseApiResponse<PostResult>

    @PATCH("/api/post/{postId}/deletion")
    suspend fun patchPostDelete(
        @Path("postId") postId: Int
    ): BaseApiResponse<Int>

    @PATCH("/api/post/{postId}/end-poll")
    suspend fun patchPostFinish(
        @Path("postId") postId: Int
    ): BaseApiResponse<PostResult>

    @GET("/api/post/{postId}")
    suspend fun fetchPost(
        @Path("postId") postId: Int,
    ): BaseApiResponse<PostResult>

    @GET("/api/post/temporary")
    suspend fun fetchTemporaryPost() : BaseApiResponse<List<PostResult>>

    @GET("/api/post/received")
    suspend fun fetchPostList(
        @Query("page") page: Int,
        @Query("count") count: Int
    ): BaseApiResponse<List<PostResult>>

    @GET("/api/post/ongoing-list")
    suspend fun fetchOnGoingPostList(
        @Query("page") page: Int,
        @Query("count") count: Int
    ): BaseApiResponse<List<PostResult>>

    @GET("/api/post/closed-list")
    suspend fun fetchClosedPostList(
        @Query("page") page: Int,
        @Query("count") count: Int
    ): BaseApiResponse<List<PostResult>>
}
