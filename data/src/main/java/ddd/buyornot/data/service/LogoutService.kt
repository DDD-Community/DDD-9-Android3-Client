package ddd.buyornot.data.service

import ddd.buyornot.data.model.BaseApiResponse
import retrofit2.http.POST

interface LogoutService {

    @POST("/auth/logout")
    suspend fun postLogout(): BaseApiResponse<String>

    @POST("/auth/sign-out")
    suspend fun postSignOut(): BaseApiResponse<String>

}