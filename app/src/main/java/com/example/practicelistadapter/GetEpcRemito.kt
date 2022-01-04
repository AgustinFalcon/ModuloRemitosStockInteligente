package com.example.practicelistadapter

import com.example.practicelistadapter.data.Remito

data class GetEpcRemito (
    val epc: String,
    val articulo: String
){
    override fun equals(other: Any?): Boolean {
        if(javaClass != other?.javaClass){
            return false
        }
        other as GetEpcRemito
        if(epc != other.epc){
            return false
        }
        if(articulo != other.articulo){
            return false
        }
        return true
    }


    override fun toString(): String {
        return "Etiquetas (Epc: $epc, Articulo: $articulo)"
    }
}