package com.payconiq.app.di.network_module

import com.payconiq.app.network_interceptors.CustomHttpLoggingInterceptor
import com.payconiq.app.api.ApiService
import com.payconiq.app.globals.BASE_URL
import com.payconiq.app.globals.TIME_OUT_OKHTTP_REQUEST
import com.payconiq.app.network_interceptors.ApiHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient as OkHttpClient1

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesBaseUrl(): String {
        return BASE_URL
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(CustomHttpLoggingInterceptor())
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, apiHeaderInterceptor: ApiHeaderInterceptor): OkHttpClient1 {
        val okHttpClient = OkHttpClient1().newBuilder()
        okHttpClient.callTimeout(TIME_OUT_OKHTTP_REQUEST, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(TIME_OUT_OKHTTP_REQUEST, TimeUnit.SECONDS)
        okHttpClient.readTimeout(TIME_OUT_OKHTTP_REQUEST, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(TIME_OUT_OKHTTP_REQUEST, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(apiHeaderInterceptor)
        okHttpClient.addNetworkInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient1, baseUrl: String, converterFactory: Converter.Factory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .addConverterFactory(converterFactory)
                    .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}