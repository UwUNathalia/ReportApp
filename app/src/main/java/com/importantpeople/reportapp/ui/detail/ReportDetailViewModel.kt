package com.importantpeople.reportapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.importantpeople.reportapp.data.model.Report
import com.importantpeople.reportapp.data.repository.ReportRepository
import com.importantpeople.reportapp.di.ServiceLocator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ReportDetailUiState(
    val isLoading: Boolean = true,
    val report: Report? = null
)

class ReportDetailViewModel(
    private val repository: ReportRepository = ServiceLocator.reportRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReportDetailUiState())
    val uiState: StateFlow<ReportDetailUiState> = _uiState.asStateFlow()

    private var loadedId: String? = null

    fun load(reportId: String) {
        if (loadedId == reportId) return
        loadedId = reportId
        _uiState.value = ReportDetailUiState(isLoading = true)
        viewModelScope.launch {
            _uiState.value = ReportDetailUiState(isLoading = false, report = repository.getReport(reportId))
        }
    }
}
