package com.importantpeople.reportapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.importantpeople.reportapp.data.model.ReportCategory

/** Ícono de cada tipo de problema. */
val ReportCategory.icon: ImageVector
    get() = when (this) {
        ReportCategory.POTHOLE -> Icons.Filled.Warning
        ReportCategory.TRASH -> Icons.Filled.Delete
        ReportCategory.LIGHTING -> Icons.Filled.Lightbulb
        ReportCategory.SIDEWALK -> Icons.Filled.Accessibility
        ReportCategory.ROAD_WORK -> Icons.Filled.Construction
        ReportCategory.OTHER -> Icons.Filled.MoreHoriz
    }

/** Color de cada tipo de problema. */
val ReportCategory.color: Color
    get() = when (this) {
        ReportCategory.POTHOLE -> Color(0xFFE8710A)
        ReportCategory.TRASH -> Color(0xFF34A853)
        ReportCategory.LIGHTING -> Color(0xFFF9AB00)
        ReportCategory.SIDEWALK -> Color(0xFF1A73E8)
        ReportCategory.ROAD_WORK -> Color(0xFF795548)
        ReportCategory.OTHER -> Color(0xFF5F6368)
    }

/** Círculo de color con el ícono de la categoría. */
@Composable
fun CategoryIcon(category: ReportCategory, modifier: Modifier = Modifier, size: Dp = 48.dp) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(category.color),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = category.icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(size * 0.5f)
        )
    }
}
