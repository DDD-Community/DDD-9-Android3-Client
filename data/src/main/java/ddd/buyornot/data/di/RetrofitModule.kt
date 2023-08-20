package ddd.buyornot.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ddd.buyornot.data.service.LoginService
import ddd.buyornot.data.service.PostService
import ddd.buyornot.data.service.PollService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {

    companion object {
        const val BASE_URL = "http://Buyornot-env.eba-f34a7fzj.ap-northeast-2.elasticbeanstalk.com"
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
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
    fun providePostService(
        retrofit: Retrofit
    ): PostService =
        retrofit.create(PostService::class.java)
}