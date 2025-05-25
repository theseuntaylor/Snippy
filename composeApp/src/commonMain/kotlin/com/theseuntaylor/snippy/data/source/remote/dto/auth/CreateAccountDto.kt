package com.theseuntaylor.snippy.data.source.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountDto(
    val email: String,
)