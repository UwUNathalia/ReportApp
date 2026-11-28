package com.importantpeople.reportapp.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.ui.components.EmptyState
import com.importantpeople.reportapp.ui.components.rememberReportPhoto
import com.importantpeople.reportapp.util.Formatters

/** Detalle de un reporte con su estado (basado en el mockup "Detalles del Reporte"). */
@Composable
fun ReportDetailScreen(
    reportId: String,
    viewModel: ReportDetailViewModel = viewModel()
) {
    LaunchedEffect(reportId) { viewModel.load(reportId) }
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val report = state.report

    when {
        state.isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        report == null -> EmptyState(
            icon = Icons.Filled.SearchOff,
            title = stringResource(R.string.detail_not_found)
        )

        else -> ReportDetailContent(
            category = report.category,
            location = report.address.ifBlank { Formatters.coordinates(report.latitude, report.longitude) },
            status = report.status,
            date = Formatters.date(report.createdAt),
            comment = report.comment.ifBlank { stringResource(R.string.detail_no_comment) },
            photo = rememberReportPhoto(report)
        )
    }
}
