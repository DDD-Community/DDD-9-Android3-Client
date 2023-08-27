package ddd.buyornot.data.repository.post

import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.source.post.PostRemoteDataSource
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource
) : PostRepository {

    override suspend fun postNewVote(postRequest: PostRequest) =
        postRemoteDataSource.postNewVote(postRequest)

    override suspend fun postNewPostFromArchive(postRequest: PostRequest, itemId1: Int, itemId2: Int) = postRemoteDataSource.postNewPostFromArchive(postRequest, itemId1, itemId2)

    override suspend fun patchPostModify(postRequest: PostRequest, postId: Int) = postRemoteDataSource.patchPostModify(postRequest, postId)

    override suspend fun patchPostDelete(postId: Int) = postRemoteDataSource.patchPostDelete(postId)

    override suspend fun patchPostFinish(postId: Int) = postRemoteDataSource.patchPostFinish(postId)

    override suspend fun fetchPost(postId: Int) = postRemoteDataSource.fetchPost(postId)

    override suspend fun fetchTemporaryPost() = postRemoteDataSource.fetchTemporaryPost()

    override suspend fun fetchPostList(page: Int, count: Int) = postRemoteDataSource.fetchPostList(page, count)

    override suspend fun fetchOnGoingPostList(page: Int, count: Int) = postRemoteDataSource.fetchOnGoingPostList(page, count)

    override suspend fun fetchClosedPostList(page: Int, count: Int) = postRemoteDataSource.fetchClosedPostList(page, count)
}