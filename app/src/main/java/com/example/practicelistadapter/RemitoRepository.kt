package com.example.practicelistadapter

import com.example.practicelistadapter.data.Remito
import com.example.practicelistadapter.data.RemitoResponse
import javax.inject.Inject

class RemitoRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getAllRemitos() = apiService.getAllRemitos()

    suspend fun sendRemitos(remitos: HashMap<Int, String>) = apiService.sendRemitos(remitos)
}