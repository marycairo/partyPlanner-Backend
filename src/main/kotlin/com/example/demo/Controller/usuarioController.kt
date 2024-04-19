package com.example.demo.Controller

import com.example.demo.Services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsuarioController (@Autowired val userService : UsuarioService) {

    @GetMapping("/Usuario")
    fun getUsuario() = userService.getUser()

    }