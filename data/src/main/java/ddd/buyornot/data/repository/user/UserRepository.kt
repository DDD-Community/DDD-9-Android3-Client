package ddd.buyornot.data.repository.user

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.user.UserResult

interface UserRepository {

    suspend fun fetchProfile() : BaseApiResponse<UserResult>?
}