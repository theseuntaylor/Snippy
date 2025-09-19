package com.theseuntaylor.snippy.data.repository.auth

import com.theseuntaylor.snippy.data.source.remote.api.auth.AuthApi
import com.theseuntaylor.snippy.data.source.remote.dto.auth.CreateAccountRequestDto
import com.theseuntaylor.snippy.data.source.remote.dto.auth.LoginRequestDto
import com.theseuntaylor.snippy.model.auth.CreateAccount
import com.theseuntaylor.snippy.model.auth.Login
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

    suspend fun loginUser(
        loginRequestDto: LoginRequestDto,
    ): Login? = authApi.loginUser(
        loginRequestDto = loginRequestDto
    )?.let {
        val user = it.data.user
        Login(email = user.email, firstName = user.first_name, lastName = user.last_name)
    }


}