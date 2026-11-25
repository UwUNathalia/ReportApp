package com.importantpeople.reportapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.importantpeople.reportapp.data.model.ReportStatus

/** Color de cada estado (como en el mockup de detalle). */
val ReportStatus.color: Color
    get() = when (this) {
        ReportStatus.PENDING -> Color(0xFFF9AB00)
        ReportStatus.IN_REVIEW -> Color(0xFF1A73E8)
        ReportStatus.VALIDATED -> Color(0xFF34A853)
        ReportStatus.FIXED -> Color(0xFF137333)
        ReportStatus.DUPLICATE -> Color(0xFFE8710A)
        ReportStatus.REJECTED -> Color(0xFFD93025)
    }

/** Ícono de cada estado. */
val ReportStatus.icon: ImageVector
    get() = when (this) {
        ReportStatus.PENDING -> Icons.Filled.HourglassEmpty
        ReportStatus.IN_REVIEW -> Icons.Filled.Search
        ReportStatus.VALIDATED -> Icons.Filled.CheckCircle
        ReportStatus.FIXED -> Icons.Filled.Star
        ReportStatus.DUPLICATE -> Icons.Filled.Flag
        ReportStatus.REJECTED -> Icons.Filled.Block
    }

/** Etiqueta pequeña con el estado del reporte. */
@Composable
fun StatusChip(status: ReportStatus, modifier: Modifier = Modifier) {
    Surface(
        color = status.color,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(status.labelRes),
            color = Color.White,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
        )
    }
}

/** Casilla de seguimiento: resalta el estado actual del reporte. */
@Composable
fun StatusTile(status: ReportStatus, active: Boolean, modifier: Modifier = Modifier) {
    OutlinedCard(
        border = BorderStroke(if (active) 3.dp else 1.dp, status.color),
        modifier = modifier
            .padding(4.dp)
            .alpha(if (active) 1f else 0.4f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 76.dp)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(status.icon, contentDescription = null, tint = status.color, modifier = Modifier.size(28.dp))
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(status.labelRes),
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}
