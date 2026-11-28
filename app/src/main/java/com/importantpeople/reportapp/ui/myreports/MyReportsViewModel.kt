package com.importantpeople.reportapp.ui.myreports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.importantpeople.reportapp.data.model.Report
import com.importantpeople.reportapp.data.repository.AuthRepository
import com.importantpeople.reportapp.data.repository.ReportRepository
import com.importantpeople.reportapp.di.ServiceLocator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MyReportsViewModel(
    authRepository: AuthRepository = ServiceLocator.authRepository,
    reportRepository: ReportRepository = ServiceLocator.reportRepository
) : ViewModel() {

    /** Reportes del usuario; null mientras se cargan. */
    val reports: StateFlow<List<Report>?> = reportRepository
        .observeUserReports(authRepository.currentUserId().orEmpty())
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)
}
