package com.theseuntaylor.snippy.data.source.remote.api.auth

import com.theseuntaylor.snippy.data.source.remote.dto.auth.CreateAccountDto
import com.theseuntaylor.snippy.data.source.remote.dto.auth.CreateAccountRequestDto
import com.theseuntaylor.snippy.data.source.remote.dto.auth.LoginDto
import com.theseuntaylor.snippy.data.source.remote.dto.auth.LoginRequestDto
import com.theseuntaylor.snippy.data.source.remote.helper.toDto
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import me.tatarka.inject.annotations.Inject

@Inject
class AuthApi(
    private val client: HttpClient,
) {
    suspend fun createUserAccount(
        createAccountRequestDto: CreateAccountRequestDto,
    ): CreateAccountDto? {
        val response = client.post(urlString = "auth/signup") {
            setBody(createAccountRequestDto)
        }

        return response.toDto()
    }

    suspend fun loginUser(
        loginRequestDto: LoginRequestDto,
    ): LoginDto? {
        val response = client.post(urlString = "auth/login") {
            setBody(loginRequestDto)
        }

        return response.toDto()
    }
}