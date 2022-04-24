package com.payconiq.app.network_interceptors

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton
import android.util.Log.WARN
import okhttp3.internal.platform.Platform
import okhttp3.internal.platform.Platform.Companion.INFO
import java.lang.Exception

@Singleton
class CustomHttpLoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {

    private val mGson = GsonBuilder().setPrettyPrinting().create()
    private val mJsonParser = JsonParser()

    override fun log(message: String) {
        val trimMessage = message.trim { it <= ' ' }
        if (trimMessage.startsWith("{") && trimMessage.endsWith("}")
                || trimMessage.startsWith("[") && trimMessage.endsWith("]")) {
            try {
                val prettyJson = mGson.toJson(mJsonParser.parse(message))
                Platform.get().log( prettyJson,INFO, null)
            } catch (e: Exception) {
                Platform.get().log( message,WARN, e)
            }
        } else {
            Platform.get().log( message, INFO,null)
        }
    }
}