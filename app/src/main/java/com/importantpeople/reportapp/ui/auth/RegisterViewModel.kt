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

/** Estado de la pantalla de registro. */
data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirm: String = "",
    @StringRes val nameError: Int? = null,
    @StringRes val emailError: Int? = null,
    @StringRes val passwordError: Int? = null,
    @StringRes val confirmError: Int? = null,
    val isLoading: Boolean = false,
    @StringRes val errorMessage: Int? = null,
    val registered: Boolean = false
)

class RegisterViewModel(
    private val authRepository: AuthRepository = ServiceLocator.authRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) = _uiState.update { it.copy(name = value, nameError = null) }
    fun onEmailChange(value: String) = _uiState.update { it.copy(email = value, emailError = null) }
    fun onPasswordChange(value: String) = _uiState.update { it.copy(password = value, passwordError = null) }
    fun onConfirmChange(value: String) = _uiState.update { it.copy(confirm = value, confirmError = null) }

    fun register() {
        val s = _uiState.value
        val nameError = if (Validators.isValidName(s.name)) null else R.string.error_invalid_name
        val emailError = if (Validators.isValidEmail(s.email)) null else R.string.error_invalid_email
        val passwordError =
            if (Validators.isValidPassword(s.password)) null else R.string.error_invalid_password
        val confirmError =
            if (Validators.passwordsMatch(s.password, s.confirm)) null else R.string.error_password_mismatch

        if (listOf(nameError, emailError, passwordError, confirmError).any { it != null }) {
            _uiState.update {
                it.copy(
                    nameError = nameError,
                    emailError = emailError,
                    passwordError = passwordError,
                    confirmError = confirmError
                )
            }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            val result = authRepository.register(s.name.trim(), s.email.trim(), s.password)
            _uiState.update {
                result.fold(
                    onSuccess = { _ -> it.copy(isLoading = false, registered = true) },
                    onFailure = { e -> it.copy(isLoading = false, errorMessage = ErrorMessages.forAuth(e)) }
                )
            }
        }
    }

    fun onNavigated() = _uiState.update { it.copy(registered = false) }
}
