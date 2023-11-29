package ddd.buyornot.data.repository.user

import ddd.buyornot.data.source.user.UserRemoteDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun fetchProfile() = userRemoteDataSource.fetchProfile()
}