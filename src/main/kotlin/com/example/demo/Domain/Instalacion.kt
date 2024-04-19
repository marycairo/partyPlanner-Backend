package com.example.demo.Domain

class Instalacion(
    val nombreDeInstalacion: String,
    val descripcionDeInstalacion: String,
    val costoDeInstalacion : Int,
    val CapacidadInstalacion: Int,
    val LocalidadDeInstalacion : String
): Entidad() {



    override fun validar() {
        TODO("Not yet implemented")
    }


}