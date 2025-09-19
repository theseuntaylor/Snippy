package com.theseuntaylor.snippy.data.source.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val email: String,
    val password: String,
)

@Serializable
data class LoginDto(
    val data: LoginDataDto,
)

@Serializable
data class LoginDataDto(
    val access_token: String,
    val user: UserDto,
)

@Serializable
data class UserDto(
    val id: String,
    val first_name: String,
    val last_name: String,
    val email: String,
)