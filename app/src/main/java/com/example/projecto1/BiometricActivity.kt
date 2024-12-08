package com.example.projecto1

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.projecto1.ui.screens.BiometricsScreen
import com.example.projecto1.ui.theme.Projecto1Theme

class BiometricActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configuración del contenido usando Compose
        setContent {
            Projecto1Theme {
                // Usamos la pantalla de biométricos
                BiometricsScreen(activity = this)
            }
        }
    }
}