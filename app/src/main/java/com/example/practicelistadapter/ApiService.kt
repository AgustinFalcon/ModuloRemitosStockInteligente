package com.example.practicelistadapter

import com.example.practicelistadapter.data.Constans
import com.example.practicelistadapter.data.RemitoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {


    @GET(Constans.END_POINT_REMITO)
    suspend fun getAllRemitos(): Response<RemitoResponse>

    @POST(Constans.END_POINT_POST_REMITO)
    suspend fun sendRemitos(@Body remitos: HashMap<Int, String>) :Response<RemitoResponse>

    @GET(Constans.END_POINT_POST_REMITO)
    suspend fun getEpcofRemitos(): Response<GetEpcRemitoResponse>
}