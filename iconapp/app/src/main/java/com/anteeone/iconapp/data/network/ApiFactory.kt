package com.anteeone.iconapp.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    private const val HEADER_API_KEY = "Authorization"
    private const val HEADER_API_VALUE = "Bearer X0vjEUN6KRlxbp2DoUkyHeM0VOmxY91rA6BbU5j3Xu6wDodwS0McmilLPBWDUcJ1"
    private const val API_URL = "https://api.iconfinder.com/v4/"

    private val apiKeyInterceptor = Interceptor{chain ->
        val original = chain.request()
        original.newBuilder()
            .addHeader(HEADER_API_KEY, HEADER_API_VALUE)
            .build()
            .let {
                chain.proceed(it)
            }

    }

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    internal val iconApi: IconApi by lazy{
        retrofit.create(IconApi::class.java)
    }

}