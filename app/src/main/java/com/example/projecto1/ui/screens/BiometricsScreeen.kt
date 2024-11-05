package com.example.projecto1.ui.screens

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.projecto1.ui.Biometrics.BiometricPromtManager
import com.example.projecto1.ui.Biometrics.BiometricPromtManager.BiometricResult

@Composable
fun BiometricsScreen (activity: AppCompatActivity){
    val promptManager by lazy {
        BiometricPromtManager(activity)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerpadding ->

        val biometricResult by promptManager.promtResults.collectAsState(initial = null)

        val enrollLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                println("Activity Result $it")
            }
        )

        LaunchedEffect(biometricResult) {
            if(biometricResult is BiometricResult.AuthenticationNotSet) {
                if(Build.VERSION.SDK_INT >= 30) {
                    val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                        putExtra(
                            Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                        )
                    }
                    enrollLauncher.launch(enrollIntent)
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                promptManager.showBiometricPrompt(
                    title = "Sample prompt",
                    description = "Sample prompt description"
                )
            }) {
                Text(text = "Authenticate")
            }
            biometricResult?.let { result ->
                Text(
                    text = when(result) {
                        is BiometricResult.AuthenticationError -> {
                            result.error
                        }
                        BiometricResult.AuthenticationFailed -> {
                            "Authentication failed"
                        }
                        BiometricResult.AuthenticationNotSet -> {
                            "Authentication not set"
                        }
                        BiometricResult.AuthenticationSuccess -> {
                            "Authentication success"
                        }
                        BiometricResult.FeatureUnavailable -> {
                            "Feature unavailable"
                        }
                        BiometricResult.HardwareUnavailable -> {
                            "Hardware unavailable"
                        }
                    }
                )

            }
        }

    }

}