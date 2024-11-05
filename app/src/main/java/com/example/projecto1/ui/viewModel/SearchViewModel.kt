package com.example.projecto1.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecto1.ui.model.GoogleGeoResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class SearchViewModel:ViewModel() {

    var lat by mutableDoubleStateOf(0.0)
        private set
    var long by mutableDoubleStateOf(0.0)
        private set
    var address by mutableStateOf("")
        private set
    var show by mutableStateOf(false)
        private set

    fun getLocation(search: String){
        viewModelScope.launch {
            val apiKey = "AIzaSyA4uRUwSHcbfQRuEn2By0U0-oFPy73aoi4"

            val url = "https://maps.googleapis.com/maps/api/geocode/json?address=$address&key=$apiKey"
            val response = withContext(Dispatchers.IO){
                URL(url).readText()
            }
            val results = Gson().fromJson(response, GoogleGeoResult::class.java)

            if (results.results.isNotEmpty()) {
                show = true
                lat = results.results[0].geometry.location.lat
                long = results.results[0].geometry.location.lng
                address = results.results[0].formatted_address
            }else{
                Log.d("fail", "no funciona asi")
            }

        }
    }


}