package ddd.buyornot.data.source.post

import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.service.PostService
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(
    private val postService: PostService
) : PostRemoteDataSource {
    override suspend fun postNewPost(postRequest: PostRequest) = runCatching {
        postService.postNewPost(postRequest)
    }

    override suspend fun postNewPostFromArchive(postRequest: PostRequest, itemId1: Int, itemId2: Int) = runCatching {
        postService.postNewPostFromArchive(postRequest, itemId1, itemId2)
    }

    override suspend fun fetchPost(postId: Int) = runCatching {
        postService.fetchPost(postId)
    }

    override suspend fun fetchTemporaryPost() = runCatching {
        postService.fetchTemporaryPost()
    }

    override suspend fun fetchPostList(page: Int, count: Int) = runCatching {
        postService.fetchPostList(page, count)
    }

    override suspend fun fetchOnGoingPostList(page: Int, count: Int) = runCatching {
        postService.fetchOnGoingPostList(page, count)
    }

    override suspend fun fetchClosedPostList(page: Int, count: Int) = runCatching {
        postService.fetchClosedPostList(page, count)
    }
}