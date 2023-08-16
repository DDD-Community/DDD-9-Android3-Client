package ddd.buyornot.data.source.archive

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.archive.PostListResponse
import ddd.buyornot.data.service.ArchiveService
import javax.inject.Inject

class ArchiveRemoteDataSourceImpl @Inject constructor(
    private val archiveService: ArchiveService,
) : ArchiveRemoteDataSource {

    override suspend fun fetchPostList(page: Int, count: Int): Result<BaseApiResponse<PostListResponse>> = runCatching {
        archiveService.fetchArchivePostList(page, count)
    }

    override suspend fun fetchLikedPostList(page: Int, count: Int): Result<BaseApiResponse<PostListResponse>> = runCatching {
        archiveService.fetchArchiveLikedPostList(page, count)
    }
}