package com.example.projecto1.data.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecto1.data.model.ServiceModel
import kotlinx.coroutines.launch
import com.example.projecto1.data.network.RetrofitClient
import retrofit2.Response

class ServiceViewModel : ViewModel() {
    val api = RetrofitClient.api

    fun getServices(onResult: (Response<List<ServiceModel>>) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.getServices()
                onResult(response)
            } catch (exception: Exception) {
                print(exception)
            }
        }
    }

    fun showService(id: Int, onResult: (Response<ServiceModel>) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.getService(id)
                onResult(response)
            } catch (exception: Exception) {
                print(exception)
            }
        }
    }

    fun createService(service: ServiceModel, onResult: (Response<ServiceModel>) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.createService(service)
                onResult(response)
            } catch (exception: Exception) {
                println(exception)
            }
        }
    }

    fun updateService(id: Int, service: ServiceModel, onResult: (Response<ServiceModel>) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.updateService(id, service)
                onResult(response)
            } catch (exception: Exception) {
                println(exception)
            }
        }
    }

    fun deleteService(id: Int, service: ServiceModel, onResult: (Response<ServiceModel>) -> Unit){
        viewModelScope.launch {
            try {
                val response = api.deleteService(id)
                onResult(response)
            } catch (exception: Exception) {
                println(exception)
            }
        }
    }

}