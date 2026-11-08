package com.importantpeople.reportapp.ui.myreports

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.ui.components.EmptyState

/** Pestaña "Mis reportes" (estructura inicial; la lista se agrega después). */
@Composable
fun MyReportsScreen() {
    EmptyState(
        icon = Icons.AutoMirrored.Filled.List,
        title = stringResource(R.string.my_reports_empty)
    )
}
