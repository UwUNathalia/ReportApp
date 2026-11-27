package com.importantpeople.reportapp.util

import androidx.annotation.StringRes
import com.importantpeople.reportapp.R

/** Convierte errores en mensajes para el usuario (en su idioma). */
object ErrorMessages {

    @StringRes
    fun forAuth(error: Throwable): Int = R.string.error_generic

    @StringRes
    fun forData(error: Throwable): Int = R.string.error_generic
}
