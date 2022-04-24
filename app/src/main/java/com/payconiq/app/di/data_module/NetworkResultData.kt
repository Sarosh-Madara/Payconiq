package com.payconiq.app.di.data_module

sealed class NetworkResultData<out T> {
    data class Success<out T>(val data: T? = null): NetworkResultData<T>()
    data class Loading(val nothing: Nothing? = null): NetworkResultData<Nothing>()
    data class Failed(val status: String? = null, val message: String? = null): NetworkResultData<Nothing>()
    data class Exception(val exception: kotlin.Exception? = null): NetworkResultData<Nothing>()
}