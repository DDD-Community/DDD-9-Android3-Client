package ddd.buyornot.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ddd.buyornot.data.prefs.SharedPreferenceWrapper
import ddd.buyornot.data.repository.login.AuthRepository
import ddd.buyornot.data.service.ArchiveService
import ddd.buyornot.data.service.LoginService
import ddd.buyornot.data.service.PollService
import ddd.buyornot.data.service.PostService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {

    companion object {
        const val BASE_URL = "https://buyornot.shop"
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        prefWrapper: SharedPreferenceWrapper,
        authRepository: AuthRepository
    ): AuthInterceptor = AuthInterceptor(prefWrapper, authRepository)

    @Provides
    @Singleton
    @Named("RequireAuthHeader")
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    @Singleton
    @Named("NoAuthHeader")
    fun provideNoAuthHeaderOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    @Named("RequireAuthHeader")
    fun provideRetrofit(
        @Named("RequireAuthHeader") okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    @Named("NoAuthHeader")
    fun provideNoAuthHeaderRetrofit(
        @Named("NoAuthHeader") okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    fun providePollService(
        @Named("RequireAuthHeader") retrofit: Retrofit
    ): PollService =
        retrofit.create(PollService::class.java)

    @Provides
    @Singleton
    fun provideLoginService(
        @Named("NoAuthHeader") retrofit: Retrofit
    ): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun provideArchiveService(
        @Named("RequireAuthHeader") retrofit: Retrofit
    ): ArchiveService =
        retrofit.create(ArchiveService::class.java)

    @Provides
    @Singleton
    fun providePostService(
        @Named("RequireAuthHeader") retrofit: Retrofit
    ): PostService =
        retrofit.create(PostService::class.java)
}

class AuthInterceptor @Inject constructor(
    private val prefWrapper: SharedPreferenceWrapper,
    private val authRepository: AuthRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = prefWrapper.authenticationCode
        val request = chain.request()

        val response = if (accessToken.isBlank()) {
            chain.proceed(request)
        } else {
            chain.proceed(
                request.newBuilder()
                    .header("Authorization", accessToken)
                    .build()
            )
        }

        return if (response.code == 403) {
            val newAccessToken = runBlocking(Dispatchers.IO) {
                authRepository.refreshToken(prefWrapper.refreshToken)
            }.getOrNull()?.result?.accessToken ?: ""

            chain.proceed(
                request.newBuilder()
                    .header("Authorization", newAccessToken)
                    .build()
            )
        } else {
            response
        }
    }
}