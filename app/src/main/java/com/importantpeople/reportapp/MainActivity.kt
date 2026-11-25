package com.importantpeople.reportapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.importantpeople.reportapp.ui.navigation.AppNavigation
import com.importantpeople.reportapp.ui.theme.ReportAppTheme

/**
 * Única actividad de la app. Toda la interfaz está hecha con Jetpack Compose
 * y la navegación entre pantallas se define en AppNavigation.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReportAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
