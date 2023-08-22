package ddd.buyornot.data.repository.poll

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.poll.PollResponse

interface PollRepository {

    suspend fun patchPollChoice(postId: Int, choice: Int) : BaseApiResponse<PollResponse>?
}