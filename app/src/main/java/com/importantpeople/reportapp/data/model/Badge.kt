package com.importantpeople.reportapp.data.model

import androidx.annotation.StringRes
import com.importantpeople.reportapp.R

/** Insignias que se desbloquean según la cantidad de reportes (gamificación). */
enum class Badge(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    val requiredReports: Int
) {
    FIRST_REPORT(R.string.badge_first, R.string.badge_first_desc, 1),
    ACTIVE_CITIZEN(R.string.badge_active, R.string.badge_active_desc, 5),
    CITY_GUARDIAN(R.string.badge_guardian, R.string.badge_guardian_desc, 10),
    URBAN_HERO(R.string.badge_hero, R.string.badge_hero_desc, 25);

    fun isUnlocked(reportsCount: Int): Boolean = reportsCount >= requiredReports
}
