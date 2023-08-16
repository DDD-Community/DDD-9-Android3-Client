package ddd.buyornot.data.repository.post

import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.source.post.PostRemoteDataSource
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource
) : PostRepository {
    override suspend fun postNewPost(postRequest: PostRequest) = postRemoteDataSource.postNewPost(postRequest)

    override suspend fun postNewPostFromArchive(postRequest: PostRequest, itemId1: Int, itemId2: Int) = postRemoteDataSource.postNewPostFromArchive(postRequest, itemId1, itemId2)
}