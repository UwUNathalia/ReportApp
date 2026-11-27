package com.importantpeople.reportapp.ui.auth

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.data.repository.AuthRepository
import com.importantpeople.reportapp.di.ServiceLocator
import com.importantpeople.reportapp.util.ErrorMessages
import com.importantpeople.reportapp.util.Validators
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/** Estado de la pantalla de login. */
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    @StringRes val emailError: Int? = null,
    @StringRes val passwordError: Int? = null,
    val isLoading: Boolean = false,
    @StringRes val errorMessage: Int? = null,
    val loggedIn: Boolean = false
)

class LoginViewModel(
    private val authRepository: AuthRepository = ServiceLocator.authRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String) =
        _uiState.update { it.copy(email = value, emailError = null, errorMessage = null) }

    fun onPasswordChange(value: String) =
        _uiState.update { it.copy(password = value, passwordError = null, errorMessage = null) }

    fun login() {
        val state = _uiState.value
        val emailError = if (Validators.isValidEmail(state.email)) null else R.string.error_invalid_email
        val passwordError =
            if (Validators.isValidPassword(state.password)) null else R.string.error_invalid_password
        if (emailError != null || passwordError != null) {
            _uiState.update { it.copy(emailError = emailError, passwordError = passwordError) }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            val result = authRepository.login(state.email.trim(), state.password)
            _uiState.update {
                result.fold(
                    onSuccess = { _ -> it.copy(isLoading = false, loggedIn = true) },
                    onFailure = { e -> it.copy(isLoading = false, errorMessage = ErrorMessages.forAuth(e)) }
                )
            }
        }
    }

    /** Se llama después de navegar, para no navegar dos veces. */
    fun onNavigated() = _uiState.update { it.copy(loggedIn = false) }
}
