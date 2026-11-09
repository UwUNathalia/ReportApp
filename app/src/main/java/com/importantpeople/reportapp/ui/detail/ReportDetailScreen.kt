package com.importantpeople.reportapp.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.data.model.ReportCategory
import com.importantpeople.reportapp.data.model.ReportStatus

/**
 * Detalle de un reporte (basado en el mockup "Detalles del Reporte").
 * En el avance muestra un reporte de ejemplo; en la entrega final
 * se cargará el reporte real desde la base de datos.
 */
@Composable
fun ReportDetailScreen() {
    ReportDetailContent(
        category = ReportCategory.POTHOLE,
        location = stringResource(R.string.example_address_1),
        status = ReportStatus.IN_REVIEW,
        date = stringResource(R.string.example_date_1),
        comment = stringResource(R.string.example_comment_1)
    )
}
