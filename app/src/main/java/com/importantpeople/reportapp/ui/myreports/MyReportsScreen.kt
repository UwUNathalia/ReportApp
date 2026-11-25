package com.importantpeople.reportapp.ui.myreports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.data.model.ReportCategory
import com.importantpeople.reportapp.data.model.ReportStatus
import com.importantpeople.reportapp.ui.components.ReportCard
import com.importantpeople.reportapp.ui.theme.SurfaceGray
import com.importantpeople.reportapp.ui.theme.TextSecondary

/**
 * Pestaña "Mis reportes". En el avance muestra reportes de ejemplo;
 * en la entrega final la lista vendrá de la base de datos.
 */
@Composable
fun MyReportsScreen(onReportClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceGray)
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.my_reports_example_count),
            style = MaterialTheme.typography.labelLarge,
            color = TextSecondary,
            modifier = Modifier.padding(8.dp)
        )
        ReportCard(
            category = ReportCategory.POTHOLE,
            comment = stringResource(R.string.example_comment_1),
            date = stringResource(R.string.example_date_1),
            status = ReportStatus.IN_REVIEW,
            onClick = onReportClick
        )
        ReportCard(
            category = ReportCategory.LIGHTING,
            comment = stringResource(R.string.example_comment_2),
            date = stringResource(R.string.example_date_2),
            status = ReportStatus.VALIDATED,
            onClick = onReportClick
        )
        ReportCard(
            category = ReportCategory.TRASH,
            comment = stringResource(R.string.example_comment_3),
            date = stringResource(R.string.example_date_3),
            status = ReportStatus.PENDING,
            onClick = onReportClick
        )
    }
}
