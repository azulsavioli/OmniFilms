package com.moobaalabs.omnifilms.data.remote.interceptor

import com.moobaalabs.omnifilms.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val apiKey = BuildConfig.OMDB_API_KEY

        val originalUrl = originalRequest.url
        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("apikey", apiKey)
            .build()
        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}