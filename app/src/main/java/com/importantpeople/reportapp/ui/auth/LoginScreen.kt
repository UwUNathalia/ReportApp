package com.importantpeople.reportapp.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.importantpeople.reportapp.R

/** Formulario 1: inicio de sesión (Vista del patrón MVVM). */
@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onGoRegister: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Cuando el ViewModel confirma el inicio de sesión, se navega al mapa
    LaunchedEffect(state.loggedIn) {
        if (state.loggedIn) {
            viewModel.onNavigated()
            onLogin()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        AuthHeader()
        Spacer(Modifier.height(32.dp))

        AuthTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChange,
            label = R.string.hint_email,
            icon = Icons.Filled.Email,
            keyboardType = KeyboardType.Email,
            error = state.emailError
        )
        Spacer(Modifier.height(8.dp))
        PasswordField(
            value = state.password,
            onValueChange = viewModel::onPasswordChange,
            label = R.string.hint_password,
            error = state.passwordError
        )

        state.errorMessage?.let { message ->
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(message),
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.height(24.dp))

        Button(
            onClick = viewModel::login,
            enabled = !state.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
            } else {
                Text(stringResource(R.string.action_login))
            }
        }
        Spacer(Modifier.height(16.dp))
        TextButton(onClick = onGoRegister) {
            Text(stringResource(R.string.action_go_register))
        }
    }
}
