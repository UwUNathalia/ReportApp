package com.importantpeople.reportapp.di

import com.importantpeople.reportapp.data.repository.FakeReportRepository
import com.importantpeople.reportapp.data.repository.ReportRepository

/**
 * Punto único donde se crean los repositorios (inyección de dependencias manual).
 * Por ahora se usan datos de ejemplo; después solo se cambian aquí por Firebase.
 */
object ServiceLocator {
    val reportRepository: ReportRepository by lazy { FakeReportRepository() }
}
