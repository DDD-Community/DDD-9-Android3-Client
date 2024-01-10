package ddd.buyornot.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ddd.buyornot.data.prefs.SharedPreferenceWrapper
import ddd.buyornot.data.repository.login.AuthRepository
import ddd.buyornot.data.service.ArchiveService
import ddd.buyornot.data.service.ItemService
import ddd.buyornot.data.service.LoginService
import ddd.buyornot.data.service.LogoutService
import ddd.buyornot.data.service.PollService
import ddd.buyornot.data.service.PostService
import ddd.buyornot.data.service.UserService
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
    fun provideLogoutInterceptor(
        prefWrapper: SharedPreferenceWrapper
    ): LogoutInterceptor = LogoutInterceptor(prefWrapper)

    @Provides
    @Singleton
    @Named("RequireAuthHeader")
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            //.addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    @Singleton
    @Named("NoAuthHeader")
    fun provideNoAuthHeaderOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            //.addNetworkInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    @Named("LogoutAuthHeader")
    fun provideLogoutAuthHeaderOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        logoutInterceptor: LogoutInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            //.addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(logoutInterceptor)
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
    @Named("LogoutAuthHeader")
    fun provideLogoutHeaderRetrofit(
        @Named("LogoutAuthHeader") okHttpClient: OkHttpClient,
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
    fun provideLogoutService(
        @Named("LogoutAuthHeader") retrofit: Retrofit
    ): LogoutService =
        retrofit.create(LogoutService::class.java)

    @Provides
    @Singleton
    fun provideArchiveService(
        @Named("RequireAuthHeader") retrofit: Retrofit
    ): ArchiveService =
        retrofit.create(ArchiveService::class.java)

    @Provides
    @Singleton
    fun provideUserService(
        @Named("RequireAuthHeader") retrofit: Retrofit
    ): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideItemService(
        @Named("RequireAuthHeader") retrofit: Retrofit
    ): ItemService =
        retrofit.create(ItemService::class.java)

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
        val accessToken = prefWrapper.accessToken
        val request = chain.request()

        // 기존 액세스 토큰이 없으면 일단 그냥 진행 (responseCode 500 에러로 재발급)
        val response = if (accessToken.isBlank()) {
            chain.proceed(request)
        } else {
            // 기존 액세스 토큰이 있으면 리퀘스트 헤더에 토큰을 추가하고 api 호출
            chain.proceed(
                request.newBuilder()
                    .header("Authorization", accessToken)
                    .build()
            )
        }

        // 액세스 토큰이 없거나 만료되었을 경우
        return if (response.code == 500) {
            response.close()

            // 액세스 토큰을 리프레쉬 후 request에 토큰을 변경 후 다시 호출
            val newAccessToken = runBlocking(Dispatchers.IO) {
                authRepository.refreshToken(prefWrapper.accessToken, prefWrapper.refreshToken)
            }.getOrNull()?.result?.accessToken ?: ""

            // 만약 리프레쉬 토큰도 만료됐을 경우 로그아웃으로 이동
            chain.proceed(
                request.newBuilder()
                    .header("Authorization", newAccessToken)
                    .build()
            )
        } else {
            // 호출이 정상 동작했을 경우
            response
        }
    }
}

class LogoutInterceptor @Inject constructor(
    private val prefWrapper: SharedPreferenceWrapper
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = prefWrapper.accessToken
        val request = chain.request()

        return if (accessToken.isBlank()) {
            chain.proceed(request)
        } else {
            chain.proceed(
                request.newBuilder()
                    .header("Authorization", accessToken)
                    .build()
            )
        }
    }
}