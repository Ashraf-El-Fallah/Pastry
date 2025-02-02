package com.example.pastry.data.remote


sealed class NetWorkResponseState<out T : Any> {
    object Loading : NetWorkResponseState<Nothing>()
    data class Success<out T : Any>(val result: T) : NetWorkResponseState<T>()
    data class Error(
        val errorMessageResId: Int? = null,
        val exception: Throwable,
        val statusCode: Int? = null
    ) :
        NetWorkResponseState<Nothing>()
}
