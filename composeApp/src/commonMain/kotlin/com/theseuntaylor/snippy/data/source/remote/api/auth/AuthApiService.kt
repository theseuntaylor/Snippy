package com.theseuntaylor.snippy.data.source.remote.api.auth

import com.theseuntaylor.snippy.data.source.remote.dto.auth.CreateAccountDto
import com.theseuntaylor.snippy.data.source.remote.dto.auth.CreateAccountRequestDto
import com.theseuntaylor.snippy.data.source.remote.dto.shared.ErrorBody
import com.theseuntaylor.snippy.data.source.remote.utils.HttpException
import com.theseuntaylor.snippy.data.source.remote.utils.post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode

class AuthApiService(
    private val client: HttpClient,
) {
    suspend fun createUserAccount(
        createAccountRequestDto: CreateAccountRequestDto,
    ): CreateAccountDto? {
        val response = client.post(endpoint = "auth/signup") {
            setBody(createAccountRequestDto)
        }

        return if (response.status == HttpStatusCode.OK) {
            response.body() as? CreateAccountDto
        } else {
            throw HttpException(
                httpStatusCode = response.status,
                error = response.body<ErrorBody>()
            )
        }
    }
}