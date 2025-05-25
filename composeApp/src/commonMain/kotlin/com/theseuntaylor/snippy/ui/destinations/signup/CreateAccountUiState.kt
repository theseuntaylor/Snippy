package com.theseuntaylor.snippy.ui.destinations.signup

import com.theseuntaylor.snippy.core.model.AppError

sealed interface CreateAccountUiState {
    data class Success(val firstName: String) : CreateAccountUiState
    data class Error(val appError: AppError) : CreateAccountUiState
    data object Loading : CreateAccountUiState

}