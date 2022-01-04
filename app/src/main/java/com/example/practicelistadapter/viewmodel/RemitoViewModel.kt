package com.example.practicelistadapter.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicelistadapter.data.remito.get.Remito
import com.example.practicelistadapter.data.remito.post.EpcRemito
import com.example.practicelistadapter.repository.RemitoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RemitoViewModel @Inject constructor(private val remitoRepository: RemitoRepository) : ViewModel(){


    private val _responseRemito = MutableLiveData<List<Remito>>()
    val responseRemito: MutableLiveData<List<Remito>>
        get() = _responseRemito

    private val _postRemito = MutableLiveData<List<EpcRemito>>()
    val postRemito: MutableLiveData<List<EpcRemito>>
        get() = _postRemito

    val myHashMap = HashMap<String, String>()

    init {
        getAllRemitos()
        print("valor del live data en el view model = $postRemito")
    }

    private fun getAllRemitos() {
        viewModelScope.launch {
            remitoRepository.getAllRemitos().let { response ->
                if(response.isSuccessful){
                    _responseRemito.postValue(response.body()?.remitos)
                } else {
                    Log.d(TAG, "Error: ${response.code()}")
                }
            }
        }
    }

    fun sendData(remitos: HashMap<String, String>){
        viewModelScope.launch {
            remitoRepository.sendRemitos(remitos).let { response ->
                if(response.isSuccessful){
                    _postRemito.postValue(response.body()?.etiquetas)

                    //Put the data in a HashMap for access to content to compare with gun data
                    response.body()?.etiquetas?.forEach {
                        myHashMap[it.epc] = it.articulo
                    }
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