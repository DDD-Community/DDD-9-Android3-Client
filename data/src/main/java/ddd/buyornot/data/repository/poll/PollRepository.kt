package ddd.buyornot.data.repository.poll

import ddd.buyornot.data.model.BaseApiResponse

interface PollRepository {

    suspend fun patchPollChoice(postId: Int, choice: Int) : BaseApiResponse<Int>?
}