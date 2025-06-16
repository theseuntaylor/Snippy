package com.theseuntaylor.snippy.data.source.remote.api.auth

import com.theseuntaylor.snippy.data.source.remote.dto.auth.CreateAccountDto
import com.theseuntaylor.snippy.data.source.remote.dto.auth.CreateAccountRequestDto
import com.theseuntaylor.snippy.data.source.remote.dto.shared.ErrorBody
import com.theseuntaylor.snippy.core.model.HttpException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import me.tatarka.inject.annotations.Inject

@Inject
class AuthApi(
    private val client: HttpClient,
) {
    suspend fun createUserAccount(
        createAccountRequestDto: CreateAccountRequestDto,
    ): CreateAccountDto? {
        val response = client.post(urlString = "/auth/signup") {
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