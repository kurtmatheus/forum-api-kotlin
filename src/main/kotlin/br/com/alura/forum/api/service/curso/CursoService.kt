package br.com.alura.forum.api.service.curso

import br.com.alura.forum.api.exceptions.NotFoundException
import br.com.alura.forum.api.repository.curso.CursoRepository
import br.com.alura.forum.domain.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(private val repository: CursoRepository) {
    fun buscarPorId(id: Int): Curso {
        return repository.findById(id).orElseThrow {NotFoundException("Curso não encontrado")
        }
    }
}