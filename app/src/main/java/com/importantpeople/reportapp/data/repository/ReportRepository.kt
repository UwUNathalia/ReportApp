package com.importantpeople.reportapp.data.repository

import com.importantpeople.reportapp.data.model.Report
import kotlinx.coroutines.flow.Flow

/** Contrato para leer y guardar reportes (la vista y los ViewModels solo conocen esta interfaz). */
interface ReportRepository {
    /** Todos los reportes de la ciudad (para el mapa). Se actualiza solo cuando cambian. */
    fun observeReports(): Flow<List<Report>>

    /** Reportes creados por un usuario, del más reciente al más antiguo. */
    fun observeUserReports(userId: String): Flow<List<Report>>

    suspend fun getReport(id: String): Report?

    /** Guarda el reporte y devuelve su id. */
    suspend fun addReport(report: Report): Result<String>
}
