package com.importantpeople.reportapp.ui.map

import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.data.model.Report
import com.importantpeople.reportapp.ui.components.color
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

/** Dibuja un pin por reporte, del color de su estado. Al tocarlo se abre el detalle. */
fun MapView.showReportMarkers(reports: List<Report>, onClick: (Report) -> Unit) {
    overlays.removeAll { it is Marker }
    reports.forEach { report ->
        val marker = Marker(this).apply {
            position = GeoPoint(report.latitude, report.longitude)
            title = context.getString(report.category.labelRes)
            icon = ContextCompat.getDrawable(context, R.drawable.ic_marker)?.mutate()?.apply {
                setTint(report.status.color.toArgb())
            }
            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            setOnMarkerClickListener { _, _ ->
                onClick(report)
                true
            }
        }
        overlays.add(marker)
    }
    invalidate()
}
