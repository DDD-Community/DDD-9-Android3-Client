package ddd.buyornot.data.repository.poll

import ddd.buyornot.data.source.poll.PollRemoteDataSource
import javax.inject.Inject

class PollRepositoryImpl @Inject constructor(
    private val pollRemoteDataSource: PollRemoteDataSource
) : PollRepository {

    override suspend fun patchPollChoice(postId: Int, choice: Int) = pollRemoteDataSource.patchPollChoice(postId, choice)
}