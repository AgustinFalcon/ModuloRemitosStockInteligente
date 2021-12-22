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

    private val _repository = MutableLiveData<List<Remito>>()
    val responseRemito: MutableLiveData<List<Remito>>
        get() = _repository

    init {
        getAllRemitos()
    }

    private fun getAllRemitos() {
        viewModelScope.launch {
            remitoRepository.getAllRemitos().let { response ->
                if(response.isSuccessful){
                    _repository.postValue(response.body()?.remitos)
                } else {
                    Log.d(TAG, "Error: ${response.code()}")
                }
            }
        }
    }

    companion object{
        const val TAG = "RemitoViewModel"
    }

}