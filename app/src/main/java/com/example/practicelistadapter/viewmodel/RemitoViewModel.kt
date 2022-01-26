package com.example.practicelistadapter.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicelistadapter.data.remito.get.Remito
import com.example.practicelistadapter.data.remito.post.EpcRemito
import com.example.practicelistadapter.repository.RemitoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RemitoViewModel @Inject constructor(private val remitoRepository: RemitoRepository) : ViewModel(){


     val tagEpcs: List<String> = listOf("008100068750252315000024", "008100068750252314000013", "008100068750252313000024",
        "00810006875000231500006B", "008100068750002314000032", "008100068750002312000034", "00810006875000231300006C").toMutableList()


    private val _responseRemito = MutableLiveData<List<Remito>>()
    val responseRemito: MutableLiveData<List<Remito>>
        get() = _responseRemito

    private val _postRemito = MutableLiveData<List<EpcRemito>>()
    val postRemito: MutableLiveData<List<EpcRemito>>
        get() = _postRemito

    val myHashMap = HashMap<String, String>()

    init {
        getAllRemitos()
    }

    private fun getAllRemitos() {
        CoroutineScope(Dispatchers.IO).launch {
            remitoRepository.getAllRemitos().let { response ->
                if(response.isSuccessful){
                    _responseRemito.postValue(response.body()?.remitos)
                } else {
                    Log.d(TAG, "Error: ${response.code()}")
                }
            }
        }
    }

    fun sendData(remitos: HashMap<Int, String>){
        CoroutineScope(Dispatchers.IO).launch {
            remitoRepository.sendRemitos(remitos).let { response ->
                if(response.isSuccessful){
                    _postRemito.postValue(response.body()?.etiquetas)

                    //Put the data in a HashMap for access to content to compare with gun data
                    response.body()?.etiquetas?.forEach {
                        myHashMap[it.epc] = it.articulo
                    }

                    awaitFrame()

                    Log.d(TAG, "Contenido live data: ${postRemito.value}") //Porque aparece como null si se supone que lo lleno antes
                    Log.d(TAG, "Contenido de MYHASHMAP $myHashMap")
                }else{
                    Log.d(TAG, "Error code: ${response.code()}")
                    Log.d(TAG, "Error del post: ${response.errorBody()}")
                }
            }
        }
    }


    //Implement logic compare data of postRemito with data get the gun



    companion object{
        const val TAG = "RemitoViewModel"
    }

}