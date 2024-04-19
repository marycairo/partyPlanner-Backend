package com.example.demo.Domain

import java.time.LocalDateTime

class Evento
    (val nombreDelEvento: String,
     val lugar: Instalacion,
     val fechaEvento: LocalDateTime,
     val cantidadDeInvitados :Int,
     var serviciosAdquiridos : MutableList<Servicio> = mutableListOf()) {




    }