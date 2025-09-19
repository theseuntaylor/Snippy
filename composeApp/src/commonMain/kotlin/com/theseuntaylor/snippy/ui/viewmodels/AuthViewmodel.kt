package com.theseuntaylor.snippy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theseuntaylor.snippy.data.repository.auth.AuthRepository
import com.theseuntaylor.snippy.data.source.remote.dto.auth.CreateAccountRequestDto
import com.theseuntaylor.snippy.ui.destinations.signup.CreateAccountUiState
import com.theseuntaylor.snippy.core.model.AppError
import com.theseuntaylor.snippy.core.utils.toAppError
import com.theseuntaylor.snippy.data.source.remote.dto.auth.LoginRequestDto
import com.theseuntaylor.snippy.ui.destinations.login.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn
import kotlin.coroutines.cancellation.CancellationException

@Inject
@SingleIn(AppScope::class)
class AuthViewmodel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _createAccountUiState: MutableStateFlow<CreateAccountUiState> =
        MutableStateFlow(CreateAccountUiState.Initial)
    val createAccountUiState: StateFlow<CreateAccountUiState> = _createAccountUiState

    private val _loginUiState: MutableStateFlow<LoginUiState> =
        MutableStateFlow(LoginUiState.Initial)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun createUserAccount(
        createAccountRequestDto: CreateAccountRequestDto,
    ) {
        viewModelScope.launch {
            viewModelCaller(
                initialBlock = {
                    _createAccountUiState.update { CreateAccountUiState.Loading }
                },
                block = {
                    val response = authRepository
                        .createUserAccount(createAccountRequestDto = createAccountRequestDto)
                    if (response != null) {
                        _createAccountUiState.update {
                            CreateAccountUiState.Success(firstName = response.email)
                        }
                    } else {
                        _createAccountUiState.update {
                            CreateAccountUiState.Error(
                                appError = AppError.SnippyException("Something went wrong")
                            )
                        }
                    }
                },
                catchBlock = { throwable ->
                    _createAccountUiState.update {
                        CreateAccountUiState.Error(appError = throwable.toAppError())
                    }
                }
            )
        }
    }

    fun loginUser(
        loginRequestDto: LoginRequestDto,
    ) {
        viewModelScope.launch {
            viewModelCaller(
                initialBlock = {
                    _loginUiState.update { LoginUiState.Loading }
                },
                block = {
                    val response = authRepository.loginUser(loginRequestDto = loginRequestDto)
                    if (response != null) {
                        _loginUiState.update {
                            LoginUiState.Success(firstName = response.email)
                        }
                    } else {
                        _loginUiState.update {
                            LoginUiState.Error(
                                appError = AppError.SnippyException("Something went wrong")
                            )
                        }
                    }
                },
                catchBlock = { throwable ->
                    _loginUiState.update {
                        LoginUiState.Error(appError = throwable.toAppError())
                    }
                }
            )
        }
    }

    fun resetLoginUiState() {
        _loginUiState.update { LoginUiState.Initial }
    }

    private suspend fun viewModelCaller(
        initialBlock: suspend () -> Unit,
        block: suspend () -> Unit,
        catchBlock: (suspend (Throwable) -> Unit),
    ) {
        try {
            initialBlock()
            _createAccountUiState.update { CreateAccountUiState.Loading }
            block()
        } catch (cancelledException: CancellationException) {
            throw cancelledException
        } catch (e: Error) {
            throw e
        } catch (e: Throwable) {
            catchBlock.invoke(e)
            _createAccountUiState.update {
                CreateAccountUiState.Error(appError = e.toAppError())
            }
        }
    }


}