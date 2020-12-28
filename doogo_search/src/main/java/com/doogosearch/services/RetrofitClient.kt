package com.doogosearch.services

import com.doogosearch.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofitClient: Retrofit.Builder by lazy {

        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.addInterceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader("x-api-key", BuildConfig.API_KEY).build()
            chain.proceed(request)
        }.addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                Level.BODY
            else
                Level.NONE
        })

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val services: Services by lazy {
        retrofitClient
            .build()
            .create(Services::class.java)
    }
}