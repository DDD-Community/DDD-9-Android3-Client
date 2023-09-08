package ddd.buyornot.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ddd.buyornot.data.repository.user.UserRepository
import ddd.buyornot.data.repository.user.UserRepositoryImpl
import ddd.buyornot.data.service.UserService
import ddd.buyornot.data.source.user.UserRemoteDataSource
import ddd.buyornot.data.source.user.UserRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {


    @Provides
    @Singleton
    fun provideUserRemoteDataSource(
        userService: UserService
    ): UserRemoteDataSource = UserRemoteDataSourceImpl(userService)

    @Provides
    @Singleton
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource
    ): UserRepository = UserRepositoryImpl(userRemoteDataSource)

}