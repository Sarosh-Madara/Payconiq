package com.payconiq.app.network_interceptors

import com.payconiq.app.globals.GITHUB_AUTHORIZATION_HEADER
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiHeaderInterceptor @Inject constructor() : Interceptor {

    companion object {
        internal const val CONTENT_TYPE_KEY = "Accept"
        internal const val CONTENT_TYPE_VALUE = "application/json"
        internal const val AUTHORIZATION_KEY = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder().apply {
            addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
           addHeader(AUTHORIZATION_KEY, GITHUB_AUTHORIZATION_HEADER)
        }.build())
    }
}