package ddd.buyornot.data.service

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.archive.ArchiveResponse
import ddd.buyornot.data.model.archive.DeleteArchiveReq
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ArchiveService {

    // 웹에서 보관
    @POST("/api/archive/from-web")
    suspend fun postArchiveItem(
        @Query("itemUrl") itemUrl: String
    ): BaseApiResponse<ArchiveResponse>

    // 게시물에서 보관
    @POST("/api/archive/from-post/{itemId}")
    suspend fun postArchiveItem(
        @Path("itemId") id: Int
    ): BaseApiResponse<ArchiveResponse>

    @PATCH("/api/archive/pick/{archiveId}")
    suspend fun patchArchiveItemLike(
        @Path("archiveId") archiveId: Int
    ): BaseApiResponse<ArchiveResponse>

    @PATCH("/api/archive/deletion")
    suspend fun patchArchiveItemDelete(
        @Body deleteArchiveReq: DeleteArchiveReq
    ): BaseApiResponse<String>

    @GET("/api/archive/list")
    suspend fun fetchArchiveList(
        @Query("page") page: Int,
        @Query("count") count: Int,
    ): BaseApiResponse<List<ArchiveResponse>>

    // 좋아요 화면 spec out 으로 제거 예정
    @GET("/api/archive/liked-list")
    suspend fun fetchArchiveLikedPostList(
        @Query("page") page: Int,
        @Query("count") count: Int,
    ): BaseApiResponse<List<ArchiveResponse>>
}