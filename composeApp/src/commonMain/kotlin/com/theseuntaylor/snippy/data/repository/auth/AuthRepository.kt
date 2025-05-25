package com.theseuntaylor.snippy.data.repository.auth

import com.theseuntaylor.snippy.data.source.remote.api.auth.AuthApiService
import com.theseuntaylor.snippy.data.source.remote.dto.auth.CreateAccountRequestDto

class AuthRepository(private val authApiService: AuthApiService) {
    suspend fun createUserAccount(
        createAccountRequestDto: CreateAccountRequestDto,
    ): CreateAccount? = authApiService.createUserAccount(
        createAccountRequestDto = createAccountRequestDto
    )?.let {
        CreateAccount(email = it.email)
    }
}