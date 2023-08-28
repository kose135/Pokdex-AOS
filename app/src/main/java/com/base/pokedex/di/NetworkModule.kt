package com.base.pokedex.di

import android.content.Context
import com.base.pokedex.data.remote.PokeApiService
import com.base.pokedex.util.Constants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            // Level.NONE: 로그 출력하지 않음
            // Level.BASIC: 요청 및 응답의 기본 정보만 로그로 출력
            // Level.HEADERS: 요청 및 응답의 헤더 정보만 로그로 출력
            // Level.BODY: 요청 및 응답의 모든 내용을 로그로 출력
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideCache(
        @ApplicationContext app: Context,
    ): Cache {
        val cacheDirectory = File(app.cacheDir, "http-cache")
        val cacheSize = 10 * 1024 * 1024 // 10MB
        return Cache(cacheDirectory, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
//        cache: Cache,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
//            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): PokeApiService = Retrofit.Builder()
        .run {
            baseUrl(Constants.BASE_URL)
            client(okHttpClient)
            addConverterFactory(MoshiConverterFactory.create(moshi))
            build()
        }.create(PokeApiService::class.java)
}