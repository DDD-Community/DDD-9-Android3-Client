package ddd.buyornot.data.source.user

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.user.UserResult

interface UserRemoteDataSource {

    suspend fun fetchProfile() : BaseApiResponse<UserResult>?
}