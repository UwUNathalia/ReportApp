package com.importantpeople.reportapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.importantpeople.reportapp.ui.auth.LoginScreen
import com.importantpeople.reportapp.ui.auth.RegisterScreen
import com.importantpeople.reportapp.ui.map.MapScreen

/** Grafo de navegación de la aplicación. */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLogin = { navController.goHome() },
                onGoRegister = { navController.navigate(Routes.REGISTER) }
            )
        }
        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegister = { navController.goHome() },
                onGoLogin = { navController.popBackStack() }
            )
        }
        composable(Routes.MAP) {
            MapScreen()
        }
    }
}

/** Va al mapa y saca el login/registro de la pila para que "atrás" no vuelva a ellos. */
private fun NavController.goHome() {
    navigate(Routes.MAP) {
        popUpTo(Routes.LOGIN) { inclusive = true }
    }
}
