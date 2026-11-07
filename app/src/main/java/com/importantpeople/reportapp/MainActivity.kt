package com.importantpeople.reportapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.importantpeople.reportapp.databinding.ActivityMainBinding

/**
 * Actividad principal. En esta primera versión solo muestra una pantalla
 * de bienvenida para comprobar que el proyecto, el tema y los idiomas funcionan.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
