package com.theseuntaylor.snippy.data.source.remote.utils

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal suspend fun HttpClient.post(
    endpoint: String,
    block: HttpRequestBuilder.() -> Unit,
): HttpResponse = post(urlString = "${ApiServiceConstants.BASE_URL}/${endpoint}"){
        contentType(ContentType.Application.Json)
        block()
    }