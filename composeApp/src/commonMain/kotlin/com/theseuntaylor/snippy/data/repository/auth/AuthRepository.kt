package com.theseuntaylor.snippy.data.repository.auth

import com.theseuntaylor.snippy.data.source.remote.api.auth.AuthApi
import com.theseuntaylor.snippy.data.source.remote.dto.auth.CreateAccountRequestDto
import me.tatarka.inject.annotations.Inject

@Inject
class AuthRepository(private val authApi: AuthApi) {
    suspend fun createUserAccount(
        createAccountRequestDto: CreateAccountRequestDto,
    ): CreateAccount? = authApi.createUserAccount(
        createAccountRequestDto = createAccountRequestDto
    )?.let {
        CreateAccount(email = it.email)
    }
}