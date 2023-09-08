package ddd.buyornot.data.source.user

import ddd.buyornot.data.service.UserService
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserRemoteDataSource {

    override suspend fun fetchProfile() = runCatching {
        userService.fetchProfile()
    }.getOrNull()
}