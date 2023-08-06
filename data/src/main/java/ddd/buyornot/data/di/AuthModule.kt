package ddd.buyornot.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ddd.buyornot.data.prefs.SharedPreferenceWrapper
import ddd.buyornot.data.repository.login.AuthRepository
import ddd.buyornot.data.repository.login.AuthRepositoryImpl
import ddd.buyornot.data.service.LoginService
import ddd.buyornot.data.source.login.AuthLocalDataSource
import ddd.buyornot.data.source.login.AuthLocalDataSourceImpl
import ddd.buyornot.data.source.login.AuthRemoteDataSource
import ddd.buyornot.data.source.login.AuthRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun bindAuthRemoteDateSource(
        loginService: LoginService
    ): AuthRemoteDataSource = AuthRemoteDataSourceImpl(loginService)

    @Provides
    @Singleton
    fun bindAuthLocalDateSource(
        sharedPreferenceWrapper: SharedPreferenceWrapper
    ): AuthLocalDataSource = AuthLocalDataSourceImpl(sharedPreferenceWrapper)

    @Provides
    @Singleton
    fun bindAuthRepository(
        authLocalDataSource: AuthLocalDataSource,
        authRemoteDataSource: AuthRemoteDataSource
    ): AuthRepository = AuthRepositoryImpl(authLocalDataSource, authRemoteDataSource)
}
