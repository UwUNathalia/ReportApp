package com.importantpeople.reportapp.data.model

import androidx.annotation.StringRes
import com.importantpeople.reportapp.R

/** Estados por los que pasa un reporte (tomados del mockup de detalle). */
enum class ReportStatus(@StringRes val labelRes: Int) {
    PENDING(R.string.status_pending),
    IN_REVIEW(R.string.status_in_review),
    VALIDATED(R.string.status_validated),
    FIXED(R.string.status_fixed),
    DUPLICATE(R.string.status_duplicate),
    REJECTED(R.string.status_rejected);

    companion object {
        fun fromName(name: String?): ReportStatus =
            entries.firstOrNull { it.name == name } ?: PENDING
    }
}
