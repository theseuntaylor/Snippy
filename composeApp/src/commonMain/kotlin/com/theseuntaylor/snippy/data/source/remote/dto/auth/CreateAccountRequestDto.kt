package com.theseuntaylor.snippy.data.source.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountRequestDto(
    val email: String,
    val password: String,
    val first_name: String,
    val last_name: String,
)