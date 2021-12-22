package com.example.practicelistadapter

import com.example.practicelistadapter.data.Constans
import com.example.practicelistadapter.data.Remito
import com.example.practicelistadapter.data.RemitoResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET(Constans.END_POINT_REMITO)
    suspend fun getAllRemitos(): Response<RemitoResponse>
}