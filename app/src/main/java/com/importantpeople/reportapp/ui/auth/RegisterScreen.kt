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
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.ui.theme.TextSecondary

/** Formulario 2: registro de un nuevo ciudadano (Vista del patrón MVVM). */
@Composable
fun RegisterScreen(
    onRegister: () -> Unit,
    onGoLogin: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(state.registered) {
        if (state.registered) {
            viewModel.onNavigated()
            onRegister()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.title_register),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.register_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )
        Spacer(Modifier.height(24.dp))

        AuthTextField(
            value = state.name,
            onValueChange = viewModel::onNameChange,
            label = R.string.hint_name,
            icon = Icons.Filled.Person,
            error = state.nameError
        )
        Spacer(Modifier.height(8.dp))
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
            imeAction = ImeAction.Next,
            helper = R.string.helper_password,
            error = state.passwordError
        )
        Spacer(Modifier.height(8.dp))
        PasswordField(
            value = state.confirm,
            onValueChange = viewModel::onConfirmChange,
            label = R.string.hint_confirm_password,
            error = state.confirmError
        )

        state.errorMessage?.let { message ->
            Spacer(Modifier.height(8.dp))
            Text(text = stringResource(message), color = MaterialTheme.colorScheme.error)
        }
        Spacer(Modifier.height(24.dp))

        Button(
            onClick = viewModel::register,
            enabled = !state.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
            } else {
                Text(stringResource(R.string.action_register))
            }
        }
        Spacer(Modifier.height(16.dp))
        TextButton(
            onClick = onGoLogin,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(R.string.action_go_login))
        }
    }
}
