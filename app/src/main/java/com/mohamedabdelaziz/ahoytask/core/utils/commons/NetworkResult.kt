package com.mohamedabdelaziz.ahoytask.core.utils.commons

sealed class NetworkResult<out R> {
    data class Success <out T> (val data : T) : NetworkResult<T>()
    data class Failure(val errorResponse : BaseApiErrorResponse) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}
