package com.example.demo.Domain

class Usuario (
    val nombreYApellido :String,
    val username: String,
    val contrasenia : String,
): Entidad(){

    fun esValidoNombre() = nombreYApellido.isEmpty()
    fun validarNombre() {
        if(esValidoNombre()) throw RuntimeException("El nombre esta vacio")
    }
    override fun validar() {
        validarNombre()
    }
}