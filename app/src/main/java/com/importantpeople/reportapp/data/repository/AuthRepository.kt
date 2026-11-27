package com.importantpeople.reportapp.data.repository

/**
 * Contrato de autenticación. La vista y el ViewModel solo conocen esta interfaz,
 * así después se puede cambiar la implementación de ejemplo por Firebase sin tocar la UI.
 */
interface AuthRepository {
    fun isLoggedIn(): Boolean
    fun currentUserId(): String?
    fun currentUserName(): String?
    fun currentUserEmail(): String?
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun register(name: String, email: String, password: String): Result<Unit>
    fun logout()
}
