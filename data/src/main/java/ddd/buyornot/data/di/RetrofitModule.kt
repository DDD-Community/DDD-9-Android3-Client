package ddd.buyornot.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ddd.buyornot.data.prefs.SharedPreferenceWrapper
import ddd.buyornot.data.service.ArchiveService
import ddd.buyornot.data.service.ItemService
import ddd.buyornot.data.service.LoginService
import ddd.buyornot.data.service.PollService
import ddd.buyornot.data.service.PostService
import ddd.buyornot.data.service.UserService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
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
        prefWrapper: SharedPreferenceWrapper
    ): AuthInterceptor = AuthInterceptor(prefWrapper)

    @Provides
    @Singleton
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
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
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
        retrofit: Retrofit
    ): PollService =
        retrofit.create(PollService::class.java)

    @Provides
    @Singleton
    fun provideLoginService(
        retrofit: Retrofit
    ): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun provideArchiveService(
        retrofit: Retrofit
    ): ArchiveService =
        retrofit.create(ArchiveService::class.java)

    @Provides
    @Singleton
    fun provideUserService(
        retrofit: Retrofit
    ): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideItemService(
        retrofit: Retrofit
    ): ItemService =
        retrofit.create(ItemService::class.java)

    @Provides
    @Singleton
    fun providePostService(
        retrofit: Retrofit
    ): PostService =
        retrofit.create(PostService::class.java)
}

class AuthInterceptor @Inject constructor(
    private val prefWrapper: SharedPreferenceWrapper
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = prefWrapper.authenticationCode
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