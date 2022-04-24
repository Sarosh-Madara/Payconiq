package com.payconiq.app.di.glide_module

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.payconiq.app.globals.GLIDE_TIME_OUT_REQUEST
import okhttp3.OkHttpClient
import java.io.InputStream
import java.util.concurrent.TimeUnit

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(GLIDE_TIME_OUT_REQUEST, TimeUnit.SECONDS)
        builder.writeTimeout(GLIDE_TIME_OUT_REQUEST, TimeUnit.SECONDS)
        builder.connectTimeout(GLIDE_TIME_OUT_REQUEST, TimeUnit.SECONDS)
        registry.append(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(builder.build()))
    }
}