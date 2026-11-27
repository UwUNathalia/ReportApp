package com.importantpeople.reportapp.util

/**
 * Validaciones de los formularios. Se usa Regex (y no android.util.Patterns)
 * para que se puedan probar con pruebas unitarias normales.
 */
object Validators {

    private val EMAIL_REGEX = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    const val MIN_PASSWORD_LENGTH = 6
    const val MIN_NAME_LENGTH = 3

    fun isValidEmail(email: String): Boolean = EMAIL_REGEX.matches(email.trim())

    fun isValidPassword(password: String): Boolean = password.length >= MIN_PASSWORD_LENGTH

    fun isValidName(name: String): Boolean = name.trim().length >= MIN_NAME_LENGTH

    fun passwordsMatch(password: String, confirm: String): Boolean = password == confirm
}
