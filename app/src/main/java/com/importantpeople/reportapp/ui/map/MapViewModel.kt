package com.importantpeople.reportapp.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.importantpeople.reportapp.data.model.Report
import com.importantpeople.reportapp.data.repository.ReportRepository
import com.importantpeople.reportapp.di.ServiceLocator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MapViewModel(
    repository: ReportRepository = ServiceLocator.reportRepository
) : ViewModel() {

    /** Reportes que se dibujan como marcadores en el mapa. */
    val reports: StateFlow<List<Report>> = repository.observeReports()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    /** Última posición del mapa, para no perderla al cambiar de pantalla. */
    var lastLatitude: Double = MapDefaults.LAT
        private set
    var lastLongitude: Double = MapDefaults.LNG
        private set
    var lastZoom: Double = MapDefaults.ZOOM
        private set

    fun saveCamera(latitude: Double, longitude: Double, zoom: Double) {
        lastLatitude = latitude
        lastLongitude = longitude
        lastZoom = zoom
    }
}
