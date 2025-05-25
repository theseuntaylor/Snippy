package com.theseuntaylor.snippy.core.utils

import com.theseuntaylor.snippy.core.model.AppError
import com.theseuntaylor.snippy.data.source.remote.utils.HttpException

fun Exception.toSnippyException(): AppError =
    AppError.SnippyException(
        message = this.message ?: cause?.stackTraceToString() ?: "Something went wrong"
    )

fun Throwable.toSnippyException(): AppError =
    AppError.SnippyException(message = this.message ?: stackTraceToString())

fun Throwable.toAppError(): AppError =
    when {
        this is HttpException -> AppError.NetworkError(httpException = this)

        else -> this.toSnippyException()
    }