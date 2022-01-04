package com.example.practicelistadapter.data.remito.post

data class EpcRemito (
    val epc: String,
    val articulo: String
){
    override fun equals(other: Any?): Boolean {
        if(javaClass != other?.javaClass){
            return false
        }
        other as EpcRemito
        if(epc != other.epc){
            return false
        }
        if(articulo != other.articulo){
            return false
        }
        return true
    }


    override fun toString(): String {
        return "Etiquetas (epc = $epc, articulo = $articulo)"
    }
}