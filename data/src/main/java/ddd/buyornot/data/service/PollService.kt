package ddd.buyornot.data.service

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.poll.PollResponse
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface PollService {

    @PATCH("/api/post/{postId}/poll")
    suspend fun patchPollChoice(
        @Path("postId") postId: Int,
        @Query("choice") choice: Int
    ): BaseApiResponse<PollResponse>?
}