package com.importantpeople.reportapp.util

import java.text.DateFormat
import java.util.Date
import java.util.Locale

object Formatters {

    /** Fecha en el formato del idioma del celular. */
    fun date(millis: Long): String =
        DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.getDefault())
            .format(Date(millis))

    fun coordinates(latitude: Double, longitude: Double): String =
        String.format(Locale.US, "%.5f, %.5f", latitude, longitude)
}
