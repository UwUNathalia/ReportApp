package com.importantpeople.reportapp.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.data.model.Badge

/**
 * Pestaña "Perfil y recompensas". En el avance muestra valores de ejemplo;
 * los puntos reales se calculan en la entrega final.
 */
@Composable
fun ProfileScreen() {
    ProfileContent(
        name = stringResource(R.string.example_user_name),
        email = stringResource(R.string.example_user_email),
        initials = stringResource(R.string.example_initials),
        points = 30,
        reportsCount = 3,
        level = 1,
        levelProgress = 0.6f,
        pointsToNextLevel = 20,
        badges = Badge.entries.map { it to it.isUnlocked(3) },
        leaderboard = listOf(
            LeaderRow("Laura Gómez", 180),
            LeaderRow("Andrés Pérez", 120),
            LeaderRow("Camila Rojas", 90),
            LeaderRow(stringResource(R.string.example_user_name), 30, isMe = true)
        )
    )
}
