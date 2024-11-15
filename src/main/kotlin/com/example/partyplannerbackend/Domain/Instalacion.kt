package com.example.partyplannerbackend.Domain

import com.example.partyplannerbackend.DTO.UsuarioCreateDTO
import com.example.partyplannerbackend.DTO.instalacionDTO
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime
import javax.print.DocFlavor.URL
@Entity
@Table(name = "instalacion")
class Instalacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    var nombreDeInstalacion: String = "",
    @Column
    var descripcionDeInstalacion: String= "",
    @Column
    var costoDeInstalacion : Int =1,
    @Column
    var CapacidadInstalacion: Int=1,
    @Column
    var calle : String = "",
    @Column
    var altura : Int = 0,
    @Column
    var LocalidadDeInstalacion : String = "",
    @Column
    var provincia : String = "",
    @Column
    var direccion : String = calle + " " + altura + " " + LocalidadDeInstalacion + " " + provincia,
    @Column
    var montoDeReserva :Double = costoDeInstalacion * 0.15,
    @Column
    var recaudacionDeReservas : Double = 0.0,
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "instalacion_id")
    var fechasReservadas : MutableList<Reserva> = mutableListOf(),
    @Column
    val imagenPrincipal : String = "",
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "idInstalacion")
    val imagenes: MutableList<Imagen> = mutableListOf(),
    @Column
    var numeroDeTelefono : Int = 0,
    @Column
    var mail : String = "",
    @Column
    var baños : Int = 0,
    @Column
    var terraza : Boolean = false,
    @Column
    var jardin : Boolean = false,
    @Column
    var estacionamiento : Boolean = false,
    @Column
    var alojamiento : Boolean = false,
    @Column
    var cocina : Boolean = false,
  //  @ManyToOne(fetch = FetchType.EAGER)
    //  @JoinColumn(name = "idUsuario")
    //  val owner :Usuario? = null,
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "instalacion_id")
    var mantenimientos : MutableList<Mantenimiento> = mutableListOf(),
    @Column
    var activo : Boolean = true
) {

    fun agregarMantenimiento(mantenimiento: Mantenimiento){
        mantenimientos.add(mantenimiento)
    }
    fun quitarMantenimiento(mantenimiento: Mantenimiento){
        mantenimientos.remove(mantenimiento)
    }
    fun costoDelaInstalacionDescontandoReserva() = this.costoDeInstalacion - montoDeReserva
    fun aniadirReserva(reserva : Reserva) = fechasReservadas.add(reserva)
    fun removerReserva(reserva : Reserva) = fechasReservadas.remove(reserva)

    fun aniadirDineroDeReserva(recaudacion : Double) = recaudacionDeReservas +recaudacion
    // Validaciones
    fun validarReserva(nuevaReserva :Reserva) {
        validarFechaMayorActual(nuevaReserva)
        validarFechaMayorIni(nuevaReserva)
        estaDisponible(nuevaReserva)
        aniadirReserva(nuevaReserva)
    }

    fun desactivacion() {
        activo = false
    }
    fun activar() {
        activo = true
    }

    fun esMayorAlaFechaActual(nuevaReserva : Reserva)= nuevaReserva.fechaIni >= LocalDateTime.now()
    fun esLaFechaFinEsMayorALaIni(nuevaReserva : Reserva)= nuevaReserva.fechaFin > nuevaReserva.fechaIni
    fun validarFechaMayorIni(nuevaReserva: Reserva) {
        if(!esLaFechaFinEsMayorALaIni(nuevaReserva)) throw RuntimeException("La fecha debe ser mayor o igual a la actual")
    }
    fun validarFechaMayorActual(nuevaReserva: Reserva) {
        if(!esMayorAlaFechaActual(nuevaReserva)) throw RuntimeException("La fecha debe ser mayor o igual a la actual")
    }
    fun estaDisponible(nuevaReserva :Reserva): Boolean {
        /*
        *   nueva reserva serva contenga dentro del rango un evento existe
        *
        * testear y validar si faltan casos
        * */
        for (reserva in fechasReservadas) {
        // 1  ambas fechas esten dentro de la reserva anterior
        if(nuevaReserva.fechaIni > reserva.fechaIni && nuevaReserva.fechaFin< reserva.fechaFin){
            throw RuntimeException("El lugar ya esta reservado en las fechas seleccionadas.")
        }
         // 2   caso fecha ini entre reserva
        if(nuevaReserva.fechaIni < reserva.fechaFin && nuevaReserva.fechaFin > reserva.fechaFin){
            throw RuntimeException("bbb")

        }
         //3 caso fecha fin dentro de la reserva anterior
        if(nuevaReserva.fechaIni < reserva.fechaIni && nuevaReserva.fechaFin > reserva.fechaIni){
            throw RuntimeException("ccc")
        }
        // 4
        if(nuevaReserva.fechaIni < reserva.fechaIni && nuevaReserva.fechaFin > reserva.fechaFin){
            throw RuntimeException("ddd")
        }

        }
        return true
    }





    fun validarFechaDisponible(nuevaReserva: Reserva) {
      //  if(estaDisponible(nuevaReserva)) throw RuntimeException("La fecha no esta disponible")
    }

    fun esValidoNombre() = nombreDeInstalacion.isEmpty()
    fun validarNombre() {
        if(esValidoNombre()) throw RuntimeException("El nombre esta vacio")
    }

    fun esValidocostoDeInstalacionn() = costoDeInstalacion > 0
    fun validarcostoDeInstalacion() {
        if(!esValidocostoDeInstalacionn()) throw RuntimeException("El costo de instalacion tiene que ser mayor a 0")
    }

    fun esValidoCapacidadInstalacion() = CapacidadInstalacion > 0
    fun validarCapacidadInstalacion() {
        if(!esValidoCapacidadInstalacion()) throw RuntimeException("La capacidad de instalacion tiene que ser mayor a 0")
    }

    fun esValidoLocalidad() = LocalidadDeInstalacion.isEmpty()
    fun validarLocalidad() {
        if(esValidoLocalidad()) throw RuntimeException("La localidad esta vacio")
    }

    fun esValidodescripcionDeInstalacion() = descripcionDeInstalacion.isEmpty()
    fun validardescripcionDeInstalacion() {
        if(esValidodescripcionDeInstalacion()) throw RuntimeException("La descripcion no puede estar vacio")
    }
    fun validacionUsernameUnique(instalacion: instalacionDTO) :Boolean{
        return instalacion.nombreDeInstalacion == nombreDeInstalacion
    }


      fun validar() {
        validarLocalidad()
        validardescripcionDeInstalacion()
        validarNombre()
        validarCapacidadInstalacion()
        validarcostoDeInstalacion()
    }


}
