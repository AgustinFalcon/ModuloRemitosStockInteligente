package com.example.practicelistadapter.data

data class Remito(
    val cantidad: String,
    val codigo: String,
    val fecha: String,
    val numero: String
){

    override fun equals(other: Any?): Boolean {
        if(javaClass != other?.javaClass){
            return false
        }
        other as Remito
        if(codigo != other.codigo){
            return false
        }
        if(numero != other.numero){
            return false
        }
        return true
    }


    override fun toString(): String {
        return "Remitos (cantidad: $cantidad, fecha: $fecha, numero: $numero)"
    }
}