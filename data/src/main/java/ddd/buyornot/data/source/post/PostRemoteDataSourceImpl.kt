package ddd.buyornot.data.source.post

import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.service.PostService
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(
    private val postService: PostService
) : PostRemoteDataSource {

    override suspend fun postNewPost(postRequest: PostRequest) = runCatching {
        postService.postNewPost(postRequest)
    }.getOrNull()

    override suspend fun postNewPostFromArchive(postRequest: PostRequest, itemId1: Int, itemId2: Int) = runCatching {
        postService.postNewPostFromArchive(postRequest, itemId1, itemId2)
    }.getOrNull()

    override suspend fun patchPostModify(postRequest: PostRequest, postId: Int) = runCatching {
        postService.patchPostModify(postRequest, postId)
    }.getOrNull()

    override suspend fun patchPostDelete(postId: Int) = runCatching {
        postService.patchPostDelete(postId)
    }.getOrNull()

    override suspend fun patchPostFinish(postId: Int) = runCatching {
        postService.patchPostFinish(postId)
    }.getOrNull()

    override suspend fun fetchPost(postId: Int) = runCatching {
        postService.fetchPost(postId)
    }.getOrNull()

    override suspend fun fetchTemporaryPost() = runCatching {
        postService.fetchTemporaryPost()
    }.getOrNull()

    override suspend fun fetchPostList(page: Int, count: Int) = runCatching {
        postService.fetchPostList(page, count)
    }.getOrNull()

    override suspend fun fetchOnGoingPostList(page: Int, count: Int) = runCatching {
        postService.fetchOnGoingPostList(page, count)
    }.getOrNull()

    override suspend fun fetchClosedPostList(page: Int, count: Int) = runCatching {
        postService.fetchClosedPostList(page, count)
    }.getOrNull()
}