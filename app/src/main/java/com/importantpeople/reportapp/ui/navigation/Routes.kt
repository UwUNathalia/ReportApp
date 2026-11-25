package com.importantpeople.reportapp.ui.navigation

import androidx.annotation.StringRes
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.data.model.ReportCategory

/** Rutas (destinos) de la navegación. */
object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"

    // Pestañas del menú inferior
    const val MAP = "map"
    const val MY_REPORTS = "my_reports"
    const val PROFILE = "profile"

    // Flujo para crear un reporte: mapa -> opciones -> formulario
    const val ARG_LAT = "lat"
    const val ARG_LNG = "lng"
    const val ARG_CATEGORY = "category"
    const val CATEGORY = "category/{$ARG_LAT}/{$ARG_LNG}"
    const val CREATE_REPORT = "create/{$ARG_CATEGORY}/{$ARG_LAT}/{$ARG_LNG}"
    fun category(lat: Double, lng: Double) = "category/$lat/$lng"
    fun createReport(category: ReportCategory, lat: Double, lng: Double) =
        "create/${category.name}/$lat/$lng"

    // Detalle de un reporte
    const val DETAIL = "detail"

    /** Pantallas sin barra superior. */
    val authRoutes = setOf(LOGIN, REGISTER)

    /** Pantallas con menú inferior. */
    val tabRoutes = setOf(MAP, MY_REPORTS, PROFILE)

    @StringRes
    fun titleFor(route: String?): Int = when (route) {
        MAP -> R.string.title_map
        MY_REPORTS -> R.string.title_my_reports
        PROFILE -> R.string.title_profile
        CATEGORY -> R.string.title_category
        CREATE_REPORT -> R.string.title_create_report
        DETAIL -> R.string.title_report_detail
        else -> R.string.app_name
    }
}
