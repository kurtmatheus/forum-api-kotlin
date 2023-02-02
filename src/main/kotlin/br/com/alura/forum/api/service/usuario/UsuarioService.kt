package br.com.alura.forum.api.service.usuario

import br.com.alura.forum.api.exceptions.NotFoundException
import br.com.alura.forum.api.repository.usuario.UsuarioRepository
import br.com.alura.forum.domain.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) {
    fun buscarPorId(id: Int): Usuario {
        return repository.findById(id).orElseThrow{NotFoundException("Usuário não encontrado")}
    }
}