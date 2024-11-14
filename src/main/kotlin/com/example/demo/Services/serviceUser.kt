package com.example.demo.Services

import com.example.demo.Repositorio.RepoUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioService {


    @Autowired
    lateinit var repoUsuario: RepoUser

    fun getUser() = repoUsuario.allInstances()
    //fun getUser(id: Int) = repoUsuario.getById(id)

    fun deleteUser(id: Int) {
        repoUsuario.delete(repoUsuario.getById(id))
    }



}
