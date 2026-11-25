package com.importantpeople.reportapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.data.model.ReportCategory
import com.importantpeople.reportapp.data.model.ReportStatus
import com.importantpeople.reportapp.ui.components.StatusTile
import com.importantpeople.reportapp.ui.components.color
import com.importantpeople.reportapp.ui.components.icon
import com.importantpeople.reportapp.ui.theme.TextSecondary

/** Contenido de "Detalles del reporte" (basado en el mockup). */
@Composable
fun ReportDetailContent(
    category: ReportCategory,
    location: String,
    status: ReportStatus,
    date: String,
    comment: String,
    modifier: Modifier = Modifier,
    photo: ImageBitmap? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Foto (o el ícono de la categoría si no hay foto)
        if (photo != null) {
            Image(
                bitmap = photo,
                contentDescription = stringResource(R.string.form_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(category.color),
                contentAlignment = Alignment.Center
            ) {
                Icon(category.icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(96.dp))
            }
        }

        Spacer(Modifier.height(16.dp))
        Text(stringResource(category.labelRes), style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(6.dp))
        Text(stringResource(R.string.detail_gps, location), style = MaterialTheme.typography.bodyMedium)
        Text(
            text = stringResource(R.string.detail_status, stringResource(status.labelRes)),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(R.string.detail_date, date),
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary
        )

        Spacer(Modifier.height(16.dp))
        Text(stringResource(R.string.detail_comment), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(4.dp))
        Text(comment, style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(16.dp))
        Text(stringResource(R.string.detail_tracking), style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(4.dp))
        ReportStatus.entries.chunked(3).forEach { row ->
            Row(modifier = Modifier.fillMaxWidth()) {
                row.forEach { tileStatus ->
                    StatusTile(
                        status = tileStatus,
                        active = tileStatus == status,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
