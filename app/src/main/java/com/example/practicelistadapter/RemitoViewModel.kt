package com.example.practicelistadapter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicelistadapter.data.Remito
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RemitoViewModel @Inject constructor(private val remitoRepository: RemitoRepository) : ViewModel(){

    private val _responseRemito = MutableLiveData<List<Remito>>()
    val responseRemito: MutableLiveData<List<Remito>>
        get() = _responseRemito

    private val _postRemito = MutableLiveData<List<Remito>>()
    val postRemito: MutableLiveData<List<Remito>>
        get() = _postRemito

    init {
        getAllRemitos()
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

    fun postRemitos(remitos: HashMap<Int, String>){
        viewModelScope.launch {
            remitoRepository.sendRemitos(remitos).let { response ->
                if(response.isSuccessful){
                    _postRemito.postValue(response.body()?.remitos)
                }else{
                    Log.d(TAG, "Error code: ${response.code()}")
                    Log.d(TAG, "Error del post: ${response.errorBody()}")
                }
            }
        }
    }

    companion object{
        const val TAG = "RemitoViewModel"
    }

}