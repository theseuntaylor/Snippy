package com.theseuntaylor.snippy.data.source.remote.dto.shared

import kotlinx.serialization.Serializable

@Serializable
data class ErrorBody(val errors: List<String>)
