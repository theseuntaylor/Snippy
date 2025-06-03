package com.theseuntaylor.snippy.di

import com.theseuntaylor.snippy.core.utils.ApiServiceConstants
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import me.tatarka.inject.annotations.Provides
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface SharedApplicationComponent {

    val json: Json
        @Provides get() = Json {
            isLenient = true; ignoreUnknownKeys = true; useAlternativeNames = false
        }

    @Provides
    fun getHttpClientEngine(): HttpClientEngine

    @Provides
    fun httpClient(): HttpClient = createHttpClient(getHttpClientEngine(), json)
}

fun createHttpClient(httpClientEngine: HttpClientEngine, json: Json) = HttpClient(httpClientEngine) {
        expectSuccess = true

        defaultRequest {
            url(ApiServiceConstants.BASE_URL)
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(json)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }