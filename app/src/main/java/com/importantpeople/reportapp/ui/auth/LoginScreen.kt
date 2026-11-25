package com.importantpeople.reportapp.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.importantpeople.reportapp.R

/**
 * Formulario 1: inicio de sesión.
 * En el avance solo se muestra la pantalla y se navega; la autenticación
 * real se conecta en la entrega final.
 */
@Composable
fun LoginScreen(onLogin: () -> Unit, onGoRegister: () -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

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
            value = email,
            onValueChange = { email = it },
            label = R.string.hint_email,
            icon = Icons.Filled.Email,
            keyboardType = KeyboardType.Email
        )
        Spacer(Modifier.height(8.dp))
        PasswordField(
            value = password,
            onValueChange = { password = it },
            label = R.string.hint_password
        )
        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onLogin,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(stringResource(R.string.action_login))
        }
        Spacer(Modifier.height(16.dp))
        TextButton(onClick = onGoRegister) {
            Text(stringResource(R.string.action_go_register))
        }
    }
}
