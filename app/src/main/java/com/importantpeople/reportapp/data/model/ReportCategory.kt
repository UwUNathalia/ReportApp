package com.importantpeople.reportapp.data.model

import androidx.annotation.StringRes
import com.importantpeople.reportapp.R

/** Tipos de problema que el ciudadano puede reportar. */
enum class ReportCategory(@StringRes val labelRes: Int, @StringRes val descriptionRes: Int) {
    POTHOLE(R.string.cat_pothole, R.string.cat_pothole_desc),
    TRASH(R.string.cat_trash, R.string.cat_trash_desc),
    LIGHTING(R.string.cat_lighting, R.string.cat_lighting_desc),
    SIDEWALK(R.string.cat_sidewalk, R.string.cat_sidewalk_desc),
    ROAD_WORK(R.string.cat_road_work, R.string.cat_road_work_desc),
    OTHER(R.string.cat_other, R.string.cat_other_desc);

    companion object {
        /** Convierte el texto guardado (por ejemplo en la base de datos) en categoría. */
        fun fromName(name: String?): ReportCategory =
            entries.firstOrNull { it.name == name } ?: OTHER
    }
}
