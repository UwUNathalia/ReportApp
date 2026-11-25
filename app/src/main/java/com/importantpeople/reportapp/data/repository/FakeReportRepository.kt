package com.importantpeople.reportapp.data.repository

import com.importantpeople.reportapp.data.model.Report
import com.importantpeople.reportapp.data.model.ReportCategory
import com.importantpeople.reportapp.data.model.ReportStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.util.UUID

/**
 * Reportes de ejemplo en memoria (Bogotá). Sirve para probar las pantallas
 * sin backend y para las pruebas unitarias.
 */
class FakeReportRepository : ReportRepository {

    private val reports = MutableStateFlow(sampleReports())

    override fun observeReports(): Flow<List<Report>> = reports

    override fun observeUserReports(userId: String): Flow<List<Report>> =
        reports.map { list ->
            list.filter { it.userId == userId }.sortedByDescending { it.createdAt }
        }

    override suspend fun getReport(id: String): Report? =
        reports.value.firstOrNull { it.id == id }

    override suspend fun addReport(report: Report): Result<String> {
        delay(FAKE_NETWORK_DELAY)
        val id = UUID.randomUUID().toString()
        reports.update { it + report.copy(id = id) }
        return Result.success(id)
    }

    private fun sampleReports(): List<Report> {
        val hour = 60 * 60 * 1000L
        val now = System.currentTimeMillis()
        return listOf(
            Report("r1", COMMUNITY, ReportCategory.POTHOLE, "Hueco grande en el carril derecho",
                4.6486, -74.0628, "Calle 72 # 10-34", ReportStatus.IN_REVIEW, now - 5 * hour),
            Report("r2", COMMUNITY, ReportCategory.LIGHTING, "Poste sin luz desde hace una semana",
                4.6097, -74.0817, "Carrera 7 # 24-89", ReportStatus.VALIDATED, now - 30 * hour),
            Report("r3", COMMUNITY, ReportCategory.TRASH, "Acumulación de basura en la esquina",
                4.6280, -74.0646, "Calle 45 # 13-20", ReportStatus.PENDING, now - 2 * hour),
            Report("r4", COMMUNITY, ReportCategory.SIDEWALK, "Andén roto, peligroso para peatones",
                4.6012, -74.0721, "Calle 19 # 3-50", ReportStatus.FIXED, now - 96 * hour),
            Report("r5", COMMUNITY, ReportCategory.ROAD_WORK, "Obra sin señalización",
                4.6669, -74.0538, "Calle 93 # 11-27", ReportStatus.DUPLICATE, now - 50 * hour)
        )
    }

    private companion object {
        const val COMMUNITY = "community"
        const val FAKE_NETWORK_DELAY = 700L
    }
}
