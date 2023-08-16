package ddd.buyornot.data.repository.post

import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.source.post.PostRemoteDataSource
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource
) : PostRepository {
    override suspend fun postNewPost(postRequest: PostRequest) = postRemoteDataSource.postNewPost(postRequest)

    override suspend fun postNewPostFromArchive(postRequest: PostRequest, itemId1: Int, itemId2: Int) = postRemoteDataSource.postNewPostFromArchive(postRequest, itemId1, itemId2)

    override suspend fun fetchPost(postId: Int) = postRemoteDataSource.fetchPost(postId)

    override suspend fun fetchTemporaryPost() = postRemoteDataSource.fetchTemporaryPost()

    override suspend fun fetchPostList(page: Int, count: Int) = postRemoteDataSource.fetchPostList(page, count)

    override suspend fun fetchOnGoingPostList(page: Int, count: Int) = postRemoteDataSource.fetchOnGoingPostList(page, count)

    override suspend fun fetchClosedPostList(page: Int, count: Int) = postRemoteDataSource.fetchClosedPostList(page, count)
}