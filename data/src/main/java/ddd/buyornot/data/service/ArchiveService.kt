package ddd.buyornot.data.service

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.archive.PostListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArchiveService {

    @GET("/api/archive/list")
    suspend fun fetchArchivePostList(
        @Query("page") page: Int,
        @Query("count") count: Int,
    ) : BaseApiResponse<PostListResponse>

    @GET("/api/archive/liked-list")
    suspend fun fetchArchiveLikedPostList(
        @Query("page") page: Int,
        @Query("count") count: Int,
    ) : BaseApiResponse<PostListResponse>
}