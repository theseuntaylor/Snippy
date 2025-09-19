package com.theseuntaylor.snippy.ui.destinations.login

import com.theseuntaylor.snippy.core.model.AppError

sealed interface LoginUiState {
    data class Success(val firstName: String) : LoginUiState
    data class Error(val appError: AppError) : LoginUiState
    data object Loading : LoginUiState
    data object Initial : LoginUiState
}