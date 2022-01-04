package com.example.practicelistadapter.repository

import com.example.practicelistadapter.service.ApiService
import javax.inject.Inject

class RemitoRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getAllRemitos() = apiService.getAllRemitos()

    suspend fun sendRemitos(remitos: HashMap<String, String>) = apiService.sendRemitos(remitos)

    //suspend fun getEpcofRemitos() = apiService.getEpcofRemitos()
}