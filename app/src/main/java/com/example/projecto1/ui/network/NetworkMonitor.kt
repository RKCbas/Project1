package com.example.projecto1.ui.network

import android.Manifest
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.TrafficStats
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import kotlinx.coroutines.delay

class NetworkMonitor(
    private val wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager,
    private val activity: AppCompatActivity
) {
    private fun getConnectionStatus(): String {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )
        val isWifiConnected = wifiManager.isWifiEnabled && networkCapabilities?.hasTransport(
            NetworkCapabilities.TRANSPORT_WIFI
        ) == true
        val isMobileConnected = networkCapabilities?.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        ) == true

        return when {
            isWifiConnected -> {
                if (
                    ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val wifiInfo: WifiInfo? = wifiManager.connectionInfo
                    val ssid = wifiInfo?.ssid?.replace("\"", "") ?: "Desconocido"
                    "Conectado a wifi: $ssid"
                } else {
                    "Conectado a WIFI (NOMBRE NO DISPONIBLE)"
                }
            }

            isMobileConnected -> "Conectado a datos móviles"

            else -> "Sin conexión a internet"
        }
    }

    fun isUsingMobileData(): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true
    }

    @Composable
    fun NetworkMonitorScreen(navController: NavController) {
        var connectionStatus by remember { mutableStateOf("Sin conexión a Internet") }
        var mobileDataUsage by remember { mutableStateOf(0L) }
        var wifiDataUsage by remember { mutableStateOf(0L) }
        var networkSpeed by remember { mutableStateOf(0) }
        var isHighQualityImage by remember { mutableStateOf(false) }

        // Registro de solicitud de permisos
        val requestPermissionLauncher = remember {
            activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (!isGranted) {
                    connectionStatus = "Permiso denegado para acceder a la información de WiFi"
                }
            }
        }

        LaunchedEffect(Unit) {
            // Comprobamos si el permiso está otorgado, si no, lo solicitamos
            if (
                ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }

            var lastMobileBytes = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes()
            var lastWifiBytes = TrafficStats.getTotalRxBytes() - lastMobileBytes

            while (true) {
                connectionStatus = getConnectionStatus()
                val isMobileConnected = isUsingMobileData()
                isHighQualityImage = !isMobileConnected

                val currentMobileBytes = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes()
                val currentWifiBytes = TrafficStats.getTotalRxBytes() - currentMobileBytes

                val mobileDataUsed = currentMobileBytes - lastMobileBytes
                val wifiDataUsed = currentWifiBytes - lastWifiBytes

                if (isMobileConnected && mobileDataUsed > 0) {
                    networkSpeed = ((mobileDataUsed * 8) / 1024).toInt()
                    mobileDataUsage += mobileDataUsed
                    lastMobileBytes = currentMobileBytes
                } else if (!isMobileConnected && wifiDataUsed > 0) {
                    networkSpeed = ((wifiDataUsed * 8) / 1024).toInt()
                    wifiDataUsage += wifiDataUsed
                    lastWifiBytes = currentWifiBytes
                } else {
                    networkSpeed = 0
                }

                delay(500L)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row {
                Button(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.KeyboardArrowLeft, "Go Back")
                }

                if (connectionStatus != "Sin conexión a Internet") {
                    NetworkImage(isHighQualityImage)
                } else {
                    Text(
                        "Sin conexión para cargar la imagen",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            ConnectionCard("Estado de la Conexión", connectionStatus, networkSpeed)
            Spacer(modifier = Modifier.height(16.dp))
            ConnectionCard("Consumo de Datos Móviles", "${mobileDataUsage / (1024 * 1024)} MB")
            Spacer(modifier = Modifier.height(16.dp))
            ConnectionCard("Consumo de Datos WiFi", "${wifiDataUsage / (1024 * 1024)} MB")
        }
    }
}
