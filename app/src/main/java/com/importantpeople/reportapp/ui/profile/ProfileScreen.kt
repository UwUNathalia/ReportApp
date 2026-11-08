package com.importantpeople.reportapp.ui.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.ui.components.EmptyState

/** Pestaña "Perfil" (estructura inicial; puntos e insignias se agregan después). */
@Composable
fun ProfileScreen() {
    EmptyState(
        icon = Icons.Filled.Person,
        title = stringResource(R.string.profile_placeholder)
    )
}
