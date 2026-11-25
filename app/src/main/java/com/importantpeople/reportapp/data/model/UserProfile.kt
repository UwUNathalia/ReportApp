package com.importantpeople.reportapp.data.model

/** Perfil del ciudadano con sus puntos (gamificación). */
data class UserProfile(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val points: Int = 0,
    val reportsCount: Int = 0
) {
    val level: Int get() = Rewards.levelFor(points)

    /** Avance dentro del nivel actual, de 0 a 1 (para la barra de progreso). */
    val levelProgress: Float
        get() = Rewards.progressInLevel(points).toFloat() / Rewards.POINTS_PER_LEVEL

    val initials: String
        get() = name.split(" ")
            .filter { it.isNotBlank() }
            .take(2)
            .joinToString("") { it.first().uppercase() }
            .ifEmpty { "?" }
}
