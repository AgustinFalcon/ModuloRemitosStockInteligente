package com.example.practicelistadapter

import androidx.navigation.Navigator
import com.example.practicelistadapter.data.Constans
import com.example.practicelistadapter.data.Remito
import com.example.practicelistadapter.data.RemitoResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    @GET(Constans.END_POINT_REMITO)
    suspend fun getAllRemitos(): Response<RemitoResponse>


    @Multipart
    @POST(Constans.END_POINT_POST_REMITO)
    suspend fun sendRemitos(@Part("remitos")remitos: HashMap<String, String>) :Response<GetEpcRemitoResponse>

    //The url for the post and this get are the same
    //@GET(Constans.END_POINT_POST_REMITO)
    //suspend fun getEpcofRemitos(): Response<GetEpcRemitoResponse>




    //First intent use @Multipart with @PartMap  || 9:34
    //Second intent use @FormUrlEncoded with @Field("remitos")  || 9:39
    //Third intent use @FormUrlEncoded with @FieldMap(encoded = true)  ||  9:47
    //Fourth intent use @Multipart with @Part("remitos")   ||  9:50 IS THIS
    //Fifth intent use @Body     || 9:54


}