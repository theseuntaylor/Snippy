package com.theseuntaylor.snippy.core.model

import com.theseuntaylor.snippy.data.source.remote.utils.HttpException

sealed class AppError {
    data class NetworkError(val httpException: HttpException) : AppError()
    data class SnippyException(val message: String) : AppError()
}