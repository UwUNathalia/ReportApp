package com.importantpeople.reportapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.importantpeople.reportapp.data.model.Report
import com.importantpeople.reportapp.util.ImageUtils

/** Decodifica la foto del reporte una sola vez (no en cada recomposición). */
@Composable
fun rememberReportPhoto(report: Report): ImageBitmap? =
    remember(report.photoBase64) {
        ImageUtils.fromBase64(report.photoBase64)?.asImageBitmap()
    }
