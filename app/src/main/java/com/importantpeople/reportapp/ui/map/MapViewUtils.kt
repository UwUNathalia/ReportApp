package com.importantpeople.reportapp.ui.map

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView

/** Posición inicial del mapa (centro de Bogotá). */
object MapDefaults {
    const val LAT = 4.6370
    const val LNG = -74.0700
    const val ZOOM = 14.0
    const val USER_ZOOM = 17.0
}

/**
 * Crea el MapView de osmdroid (OpenStreetMap) y lo conecta con el ciclo de vida
 * de la pantalla, porque es una vista clásica de Android dentro de Compose.
 */
@Composable
fun rememberMapViewWithLifecycle(
    initialLat: Double = MapDefaults.LAT,
    initialLng: Double = MapDefaults.LNG,
    initialZoom: Double = MapDefaults.ZOOM,
    onDispose: (MapView) -> Unit = {}
): MapView {
    val context = LocalContext.current
    val mapView = remember {
        // osmdroid necesita un "user agent" para descargar el mapa
        Configuration.getInstance().load(
            context,
            context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
        )
        Configuration.getInstance().userAgentValue = context.packageName

        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
            controller.setZoom(initialZoom)
            controller.setCenter(GeoPoint(initialLat, initialLng))
        }
    }

    val currentOnDispose by rememberUpdatedState(onDispose)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle, mapView) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                else -> Unit
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
            currentOnDispose(mapView)
            mapView.onDetach()
        }
    }
    return mapView
}
