package com.importantpeople.reportapp.data.model

/** Reglas de gamificación: puntos y niveles. */
object Rewards {
    const val POINTS_PER_REPORT = 10
    const val POINTS_PER_LEVEL = 50

    fun levelFor(points: Int): Int = points / POINTS_PER_LEVEL + 1

    /** Puntos acumulados dentro del nivel actual (0..49). */
    fun progressInLevel(points: Int): Int = points % POINTS_PER_LEVEL

    fun pointsToNextLevel(points: Int): Int = POINTS_PER_LEVEL - progressInLevel(points)
}
