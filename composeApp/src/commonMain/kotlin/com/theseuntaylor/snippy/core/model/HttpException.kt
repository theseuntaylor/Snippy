package com.theseuntaylor.snippy.core.model

import com.theseuntaylor.snippy.data.source.remote.dto.shared.ErrorBody
import io.ktor.http.HttpStatusCode

data class HttpException(
    val httpStatusCode: HttpStatusCode,
    val error: ErrorBody,
) : Throwable()
