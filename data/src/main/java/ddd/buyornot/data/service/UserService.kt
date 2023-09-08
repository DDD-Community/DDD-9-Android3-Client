package ddd.buyornot.data.service

import ddd.buyornot.data.model.BaseApiResponse
import ddd.buyornot.data.model.user.UserResult
import retrofit2.http.GET

interface UserService {

    @GET("/api/user/profile")
    suspend fun fetchProfile() : BaseApiResponse<UserResult>
}