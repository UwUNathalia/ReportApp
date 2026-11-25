package com.importantpeople.reportapp.data.model

/** Reporte ciudadano de un problema de infraestructura. */
data class Report(
    val id: String = "",
    val userId: String = "",
    val category: ReportCategory = ReportCategory.OTHER,
    val comment: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val address: String = "",
    val status: ReportStatus = ReportStatus.PENDING,
    val createdAt: Long = System.currentTimeMillis(),
    /** Foto comprimida en JPEG y codificada en Base64 (puede ser null). */
    val photoBase64: String? = null
)
