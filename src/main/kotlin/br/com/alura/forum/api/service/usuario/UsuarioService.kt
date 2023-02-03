package br.com.alura.forum.api.service.usuario

import br.com.alura.forum.api.exceptions.NotFoundException
import br.com.alura.forum.api.repository.usuario.UsuarioRepository
import br.com.alura.forum.api.service.usuario.config.UserDetail
import br.com.alura.forum.domain.Usuario
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val repository: UsuarioRepository
) : UserDetailsService {
    fun buscarPorId(id: Int): Usuario {
        return repository.findById(id).orElseThrow{NotFoundException("Usuário não encontrado")}
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = repository.findByEmail(username) ?: throw NotFoundException("Usuário não encontrado")
        return UserDetail(usuario)
    }
}