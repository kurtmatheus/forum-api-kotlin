package br.com.navegapp.navegapi.api.service.usuario

import br.com.navegapp.navegapi.domain.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(private var usuarios: MutableList<Usuario> = mutableListOf()) {

    init {
        val usuario: Usuario = Usuario(
            id = 1,
            nome = "Dani Sampaio",
            email = "dani@email.com"
        )

        usuarios = mutableListOf(usuario)
    }

    fun buscarPorId(id: Int): Usuario = usuarios.filter { c -> c.id == id }.first()
}