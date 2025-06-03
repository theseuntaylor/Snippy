package com.theseuntaylor.snippy.core.model

sealed class AppError {
    data class NetworkError(val httpException: HttpException) : AppError()
    data class SnippyException(val message: String) : AppError()
}