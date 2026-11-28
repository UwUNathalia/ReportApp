package com.importantpeople.reportapp.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.importantpeople.reportapp.R
import org.osmdroid.util.GeoPoint

/**
 * Pantalla 1 del flujo de reporte: mapa con los reportes de la ciudad.
 * El pin del centro indica dónde se va a crear el nuevo reporte.
 */
@Composable
fun MapScreen(
    onNewReport: (latitude: Double, longitude: Double) -> Unit,
    onReportClick: (reportId: String) -> Unit,
    viewModel: MapViewModel = viewModel()
) {
    val reports by viewModel.reports.collectAsStateWithLifecycle()
    val mapView = rememberMapViewWithLifecycle(
        initialLat = viewModel.lastLatitude,
        initialLng = viewModel.lastLongitude,
        initialZoom = viewModel.lastZoom,
        onDispose = { map ->
            // Se guarda la posición para volver al mismo lugar
            viewModel.saveCamera(map.mapCenter.latitude, map.mapCenter.longitude, map.zoomLevelDouble)
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize(),
            update = { map -> map.showReportMarkers(reports) { report -> onReportClick(report.id) } }
        )

        CenterPin(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-38).dp)
        )

        SmallFloatingActionButton(
            onClick = {
                mapView.controller.animateTo(GeoPoint(MapDefaults.LAT, MapDefaults.LNG))
            },
            containerColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 88.dp)
        ) {
            Icon(Icons.Filled.MyLocation, contentDescription = stringResource(R.string.map_my_location))
        }

        // Pantalla 2 del flujo: opciones para agregar el reporte en el centro del mapa
        ExtendedFloatingActionButton(
            onClick = {
                val center = mapView.mapCenter
                onNewReport(center.latitude, center.longitude)
            },
            icon = { Icon(Icons.Filled.Add, contentDescription = null) },
            text = { Text(stringResource(R.string.map_new_report)) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        )

        Text(
            text = stringResource(R.string.map_osm_credit),
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(4.dp)
        )
    }
}
