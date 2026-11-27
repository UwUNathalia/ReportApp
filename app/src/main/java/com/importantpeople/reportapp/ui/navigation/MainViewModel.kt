package com.importantpeople.reportapp.ui.navigation

import androidx.lifecycle.ViewModel
import com.importantpeople.reportapp.data.repository.AuthRepository
import com.importantpeople.reportapp.di.ServiceLocator

/** ViewModel de la navegación: sesión actual y cierre de sesión. */
class MainViewModel(
    private val authRepository: AuthRepository = ServiceLocator.authRepository
) : ViewModel() {

    fun isLoggedIn(): Boolean = authRepository.isLoggedIn()

    fun logout() = authRepository.logout()
}
