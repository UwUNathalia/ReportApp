package com.importantpeople.reportapp.ui.report

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.importantpeople.reportapp.R
import com.importantpeople.reportapp.data.model.ReportCategory
import java.util.Locale

/**
 * Formulario 3: crear reporte (foto, tipo, comentario y ubicación).
 * En el avance solo está la interfaz: la foto, el GPS y el envío
 * se conectan en la entrega final.
 */
@Composable
fun CreateReportScreen(
    category: ReportCategory,
    latitude: Double,
    longitude: Double,
    onSent: () -> Unit
) {
    val context = LocalContext.current
    var selected by rememberSaveable { mutableStateOf(category) }
    var comment by rememberSaveable { mutableStateOf("") }

    val comingSoon = {
        Toast.makeText(context, R.string.form_coming_soon, Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        PhotoPicker(photo = null, onCamera = comingSoon, onGallery = comingSoon)
        Spacer(Modifier.height(16.dp))

        CategoryDropdown(selected = selected, onSelected = { selected = it })
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text(stringResource(R.string.form_comment)) },
            minLines = 3,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        LocationCard(
            title = stringResource(R.string.form_gps_auto),
            subtitle = String.format(Locale.US, "%.5f, %.5f", latitude, longitude)
        )
        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                Toast.makeText(context, R.string.form_sent_demo, Toast.LENGTH_SHORT).show()
                onSent()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(stringResource(R.string.form_send))
        }
    }
}
