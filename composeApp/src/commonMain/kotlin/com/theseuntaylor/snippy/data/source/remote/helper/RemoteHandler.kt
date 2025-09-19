package com.theseuntaylor.snippy.data.source.remote.helper

import com.theseuntaylor.snippy.core.model.HttpException
import com.theseuntaylor.snippy.data.source.remote.dto.shared.ErrorBody
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

suspend inline fun <reified T> HttpResponse.toDto(): T? = if (this.status == HttpStatusCode.OK) {
    this.body() as? T
} else {
    throw HttpException(
        httpStatusCode = this.status,
        error = this.body<ErrorBody>(),
        message = this.body<ErrorBody>().errors.firstOrNull(),
    )
}