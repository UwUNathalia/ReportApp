package com.importantpeople.reportapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.importantpeople.reportapp.data.model.ReportCategory
import com.importantpeople.reportapp.ui.auth.LoginScreen
import com.importantpeople.reportapp.ui.auth.RegisterScreen
import com.importantpeople.reportapp.ui.detail.ReportDetailScreen
import com.importantpeople.reportapp.ui.map.MapScreen
import com.importantpeople.reportapp.ui.myreports.MyReportsScreen
import com.importantpeople.reportapp.ui.profile.ProfileScreen
import com.importantpeople.reportapp.ui.report.CategoryScreen
import com.importantpeople.reportapp.ui.report.CreateReportScreen

/** Grafo de navegación con la barra superior y el menú inferior. */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val route = backStackEntry?.destination?.route

    var showAbout by rememberSaveable { mutableStateOf(false) }
    var showLogout by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (route != null && route !in Routes.authRoutes) {
                ReportTopBar(
                    title = Routes.titleFor(route),
                    showBack = route !in Routes.tabRoutes,
                    onBack = { navController.navigateUp() },
                    onAbout = { showAbout = true },
                    onLogout = { showLogout = true }
                )
            }
        },
        bottomBar = {
            if (route in Routes.tabRoutes) {
                ReportBottomBar(
                    currentRoute = route,
                    onSelect = { target -> navController.openTab(target) }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.LOGIN,
            modifier = Modifier.padding(padding)
        ) {
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
                MapScreen(
                    onNewReport = { lat, lng -> navController.navigate(Routes.category(lat, lng)) }
                )
            }
            composable(Routes.MY_REPORTS) {
                MyReportsScreen(onReportClick = { navController.navigate(Routes.DETAIL) })
            }
            composable(Routes.PROFILE) { ProfileScreen() }

            composable(
                route = Routes.CATEGORY,
                arguments = listOf(
                    navArgument(Routes.ARG_LAT) { type = NavType.StringType },
                    navArgument(Routes.ARG_LNG) { type = NavType.StringType }
                )
            ) { entry ->
                CategoryScreen(
                    latitude = entry.doubleArg(Routes.ARG_LAT),
                    longitude = entry.doubleArg(Routes.ARG_LNG),
                    onCategorySelected = { category ->
                        navController.navigate(
                            Routes.createReport(
                                category,
                                entry.doubleArg(Routes.ARG_LAT),
                                entry.doubleArg(Routes.ARG_LNG)
                            )
                        )
                    }
                )
            }
            composable(
                route = Routes.CREATE_REPORT,
                arguments = listOf(
                    navArgument(Routes.ARG_CATEGORY) { type = NavType.StringType },
                    navArgument(Routes.ARG_LAT) { type = NavType.StringType },
                    navArgument(Routes.ARG_LNG) { type = NavType.StringType }
                )
            ) { entry ->
                CreateReportScreen(
                    category = ReportCategory.fromName(entry.arguments?.getString(Routes.ARG_CATEGORY)),
                    latitude = entry.doubleArg(Routes.ARG_LAT),
                    longitude = entry.doubleArg(Routes.ARG_LNG),
                    onSent = {
                        // Vuelve al mapa y cierra el flujo de reporte
                        navController.navigate(Routes.MAP) {
                            popUpTo(Routes.MAP) { inclusive = true }
                        }
                    }
                )
            }
            composable(Routes.DETAIL) { ReportDetailScreen() }
        }
    }

    if (showAbout) {
        AboutDialog(onDismiss = { showAbout = false })
    }
    if (showLogout) {
        LogoutDialog(
            onConfirm = {
                showLogout = false
                navController.navigate(Routes.LOGIN) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            },
            onDismiss = { showLogout = false }
        )
    }
}

/** Lee un argumento numérico de la ruta (se envían como texto). */
private fun NavBackStackEntry.doubleArg(name: String): Double =
    arguments?.getString(name)?.toDoubleOrNull() ?: 0.0

/** Va al mapa y saca el login/registro de la pila para que "atrás" no vuelva a ellos. */
private fun NavController.goHome() {
    navigate(Routes.MAP) {
        popUpTo(Routes.LOGIN) { inclusive = true }
    }
}

/** Cambia de pestaña sin apilar pantallas repetidas. */
private fun NavController.openTab(route: String) {
    navigate(route) {
        popUpTo(Routes.MAP) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
