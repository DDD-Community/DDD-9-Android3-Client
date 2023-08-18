package ddd.buyornot.data.source.poll

import ddd.buyornot.data.model.BaseApiResponse

interface PollRemoteDataSource {

    suspend fun patchPollChoice(postId: Int, choice: Int) : BaseApiResponse<Int>?
}