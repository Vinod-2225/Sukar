package com.designmaster.sukar.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var retrofit: Retrofit? = null

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.API_END_POINT.BASE_URL_STAGING)
                    .client(buildOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    val apiInterFace: ApiInterface
        get() {
            val retrofit = client
            return retrofit!!.create(ApiInterface::class.java)
        }


    //////STRING////////

    private var stringRetrofit: Retrofit? = null

    val stringClient: Retrofit?
        get() {
            if (stringRetrofit == null) {
                stringRetrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.API_END_POINT.BASE_URL_STAGING)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()
            }
            return stringRetrofit
        }

    val stringApiInterFace: ApiInterface
        get() {
            val retrofit = stringClient
            return retrofit!!.create(ApiInterface::class.java)
        }

    private fun buildOkHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        //set timeout
        httpClientBuilder
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)

        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClientBuilder.addInterceptor(loggingInterceptor)
        httpClientBuilder.addNetworkInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("lang", "English")
                .addHeader("Content-Type","application/x-www-form-urlencoded")
            val request = requestBuilder.build()
            chain.proceed(request!!)
        }.build()
        return httpClientBuilder.build()
    }

}