package com.importantpeople.reportapp.ui.myreports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.ui.components.EmptyState
import com.importantpeople.reportapp.ui.components.ReportCard
import com.importantpeople.reportapp.ui.components.rememberReportPhoto
import com.importantpeople.reportapp.ui.theme.SurfaceGray
import com.importantpeople.reportapp.ui.theme.TextSecondary
import com.importantpeople.reportapp.util.Formatters

/** Pestaña "Mis reportes": historial y estado de los reportes del usuario. */
@Composable
fun MyReportsScreen(
    onReportClick: (reportId: String) -> Unit,
    viewModel: MyReportsViewModel = viewModel()
) {
    val context = LocalContext.current
    val reports by viewModel.reports.collectAsStateWithLifecycle()
    val list = reports

    when {
        list == null -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        list.isEmpty() -> EmptyState(
            icon = Icons.AutoMirrored.Filled.List,
            title = stringResource(R.string.my_reports_empty),
            subtitle = stringResource(R.string.my_reports_empty_hint)
        )

        else -> LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(SurfaceGray),
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                Text(
                    text = context.resources.getQuantityString(R.plurals.my_reports_count, list.size, list.size),
                    style = MaterialTheme.typography.labelLarge,
                    color = TextSecondary,
                    modifier = Modifier.padding(8.dp)
                )
            }
            items(list, key = { it.id }) { report ->
                ReportCard(
                    category = report.category,
                    comment = report.comment.ifBlank {
                        report.address.ifBlank { Formatters.coordinates(report.latitude, report.longitude) }
                    },
                    date = Formatters.date(report.createdAt),
                    status = report.status,
                    photo = rememberReportPhoto(report),
                    onClick = { onReportClick(report.id) }
                )
            }
        }
    }
}
