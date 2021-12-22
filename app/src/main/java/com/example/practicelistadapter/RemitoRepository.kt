package com.example.practicelistadapter

import javax.inject.Inject

class RemitoRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getAllRemitos() = apiService.getAllRemitos()
}