package com.importantpeople.reportapp.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.data.model.Badge
import com.importantpeople.reportapp.ui.theme.BrandBlueDark
import com.importantpeople.reportapp.ui.theme.BrandBlueLight
import com.importantpeople.reportapp.ui.theme.BrandYellow
import com.importantpeople.reportapp.ui.theme.SurfaceGray
import com.importantpeople.reportapp.ui.theme.TextSecondary

/** Fila del ranking de la comunidad. */
data class LeaderRow(val name: String, val points: Int, val isMe: Boolean = false)

/** Contenido de "Perfil y recompensas": puntos, nivel, insignias y ranking. */
@Composable
fun ProfileContent(
    name: String,
    email: String,
    initials: String,
    points: Int,
    reportsCount: Int,
    level: Int,
    levelProgress: Float,
    pointsToNextLevel: Int,
    badges: List<Pair<Badge, Boolean>>,
    leaderboard: List<LeaderRow>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceGray)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Encabezado con estadísticas
        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(initials, color = Color.White, style = MaterialTheme.typography.headlineSmall)
                }
                Spacer(Modifier.height(8.dp))
                Text(name, style = MaterialTheme.typography.titleLarge)
                Text(email, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)

                Spacer(Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Stat(value = points, label = R.string.profile_points, modifier = Modifier.weight(1f))
                    Stat(value = reportsCount, label = R.string.profile_reports, modifier = Modifier.weight(1f))
                    Stat(value = level, label = R.string.profile_level, modifier = Modifier.weight(1f))
                }

                Spacer(Modifier.height(16.dp))
                LinearProgressIndicator(
                    progress = { levelProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.profile_next_level, pointsToNextLevel, level + 1),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }

        // Insignias
        Spacer(Modifier.height(20.dp))
        Text(stringResource(R.string.profile_badges), style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        badges.chunked(2).forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { (badge, unlocked) ->
                    BadgeCard(badge = badge, unlocked = unlocked, modifier = Modifier.weight(1f))
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
            Spacer(Modifier.height(8.dp))
        }

        // Ranking
        Spacer(Modifier.height(12.dp))
        Text(stringResource(R.string.profile_ranking), style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            leaderboard.forEachIndexed { index, row ->
                if (index > 0) HorizontalDivider()
                LeaderboardRow(position = index + 1, row = row)
            }
        }
    }
}

@Composable
private fun Stat(value: Int, label: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(stringResource(label), style = MaterialTheme.typography.labelMedium)
    }
}

private val Badge.icon: ImageVector
    get() = when (this) {
        Badge.FIRST_REPORT -> Icons.Filled.Star
        Badge.ACTIVE_CITIZEN -> Icons.Filled.CheckCircle
        Badge.CITY_GUARDIAN -> Icons.Filled.Shield
        Badge.URBAN_HERO -> Icons.Filled.EmojiEvents
    }

@Composable
private fun BadgeCard(badge: Badge, unlocked: Boolean, modifier: Modifier = Modifier) {
    ElevatedCard(modifier = modifier.alpha(if (unlocked) 1f else 0.5f)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(if (unlocked) BrandYellow else TextSecondary),
                contentAlignment = Alignment.Center
            ) {
                Icon(badge.icon, contentDescription = null, tint = Color.White)
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = stringResource(badge.titleRes),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(badge.descriptionRes),
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun LeaderboardRow(position: Int, row: LeaderRow) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(BrandBlueLight),
            contentAlignment = Alignment.Center
        ) {
            Text(position.toString(), color = BrandBlueDark, style = MaterialTheme.typography.labelLarge)
        }
        Spacer(Modifier.width(12.dp))
        Text(
            text = if (row.isMe) stringResource(R.string.profile_you, row.name) else row.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = stringResource(R.string.profile_points_value, row.points),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
