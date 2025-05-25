package com.theseuntaylor.snippy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theseuntaylor.snippy.data.repository.auth.AuthRepository
import com.theseuntaylor.snippy.data.source.remote.dto.auth.CreateAccountRequestDto
import com.theseuntaylor.snippy.ui.destinations.signup.CreateAccountUiState
import com.theseuntaylor.snippy.core.model.AppError
import com.theseuntaylor.snippy.core.utils.toAppError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class CreateAccountViewmodel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<CreateAccountUiState> =
        MutableStateFlow(CreateAccountUiState.Loading)
    val uiState: StateFlow<CreateAccountUiState> = _uiState

    fun createUserAccount(
        createAccountRequestDto: CreateAccountRequestDto,
    ) {
        viewModelScope.launch {
            viewModelCaller {
                val response = authRepository
                    .createUserAccount(createAccountRequestDto = createAccountRequestDto)
                if (response != null) {
                    _uiState.update {
                        CreateAccountUiState.Success(firstName = response.email)
                    }
                } else {
                    _uiState.update {
                        CreateAccountUiState.Error(
                            appError = AppError.SnippyException("Something went wrong")
                        )
                    }
                }
            }
        }
    }

    private suspend fun viewModelCaller(block: suspend () -> Unit) {
        try {
            _uiState.update { CreateAccountUiState.Loading }
            block()
        } catch (cancelledException: CancellationException) {
            throw cancelledException
        } catch (e: Error) {
            throw e
        } catch (e: Throwable) {
            _uiState.update {
                CreateAccountUiState.Error(appError = e.toAppError())
            }
        }
    }


}