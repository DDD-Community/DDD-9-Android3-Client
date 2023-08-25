package ddd.buyornot.data.source.poll

import ddd.buyornot.data.service.PollService
import javax.inject.Inject

class PollRemoteDataSourceImpl @Inject constructor(
    private val pollService: PollService
) : PollRemoteDataSource {

    override suspend fun patchPollChoice(postId: Int, choice: Int) = runCatching{
        pollService.patchPollChoice(postId, choice)
    }.getOrNull()
}