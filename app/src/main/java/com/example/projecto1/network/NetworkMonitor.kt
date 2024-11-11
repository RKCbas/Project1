package com.example.projecto1.network

import android.Manifest
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.TrafficStats
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat

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
                    "Conectadp a WIFI (NOMBRE NO DISPONIBLE)"
                }
            }

            isMobileConnected -> "Conectado a datos moviles"

            else -> "Sin conexion a internet"

        }
    }

//    fun isUsingMobileData(): Boolean {
//        return connectivityManager.getNetworkCapabilities(
//            connectivityManager.activeNetwork
//        )?.hasTransport()
//    }
    
    @Composable
    fun NetworkMonitorScreen(){
        var connectionStatus by remember {
            mutableStateOf("sin conexion a internet")
        }
        var mobileDataUse by remember {
            mutableStateOf( 0L )
        }
        var wifiDataUsage by remember {
            mutableStateOf( 0L )
        }
        var networkSpeed by remember {
            mutableStateOf( 0 )
        }
        var isHighQualityImage by remember {
            mutableStateOf( false )
        }

//        LaunchedEffect {
//
//            var lastMobileBytes = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes()
//            var lastWifiBytes = TrafficStats.getTotalRxBytes() - lastMobileBytes
//        }
//

    }

}