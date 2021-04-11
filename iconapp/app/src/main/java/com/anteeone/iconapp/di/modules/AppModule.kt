package com.anteeone.iconapp.di.modules

import com.anteeone.iconapp.data.network.ApiConstants
import com.anteeone.iconapp.data.network.IconApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(ApiConstants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): Interceptor = Interceptor{chain ->
        val original = chain.request()
        original.newBuilder()
            .addHeader(ApiConstants.HEADER_API_KEY, ApiConstants.HEADER_API_VALUE)
            .build()
            .let {
                chain.proceed(it)
            }
    }

    @Provides
    @Singleton
    fun provideClient(apiKeyInterceptor:Interceptor):OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()



    @Provides
    @Singleton
    fun provideIconApi(retrofit: Retrofit): IconApi =
        retrofit.create(IconApi::class.java)



}