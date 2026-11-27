package com.importantpeople.reportapp.data.repository

import kotlinx.coroutines.delay

/**
 * Autenticación en memoria (sin backend). Acepta cualquier correo y contraseña
 * con formato válido. También se usa en las pruebas unitarias.
 */
class FakeAuthRepository(private val networkDelay: Long = 600L) : AuthRepository {

    private data class LocalUser(val id: String, val name: String, val email: String)

    private val users = mutableMapOf<String, LocalUser>()
    private var currentUser: LocalUser? = null

    override fun isLoggedIn(): Boolean = currentUser != null
    override fun currentUserId(): String? = currentUser?.id
    override fun currentUserName(): String? = currentUser?.name
    override fun currentUserEmail(): String? = currentUser?.email

    override suspend fun login(email: String, password: String): Result<Unit> {
        delay(networkDelay)
        currentUser = users[email] ?: LocalUser(
            id = "local-${email.hashCode()}",
            name = email.substringBefore("@").replaceFirstChar { it.uppercase() },
            email = email
        )
        return Result.success(Unit)
    }

    override suspend fun register(name: String, email: String, password: String): Result<Unit> {
        delay(networkDelay)
        val user = LocalUser(id = "local-${email.hashCode()}", name = name, email = email)
        users[email] = user
        currentUser = user
        return Result.success(Unit)
    }

    override fun logout() {
        currentUser = null
    }
}
