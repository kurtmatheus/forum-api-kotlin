package br.com.navegapp.navegapi.api.service.curso

import br.com.navegapp.navegapi.domain.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(private var cursos: List<Curso> = listOf()) {

    init {
        val curso: Curso = Curso(
            id = 1,
            nome = "Kotlin/Spring",
            categoria = "Programação"
        )

        cursos = listOf(curso)
    }

    fun buscarPorId(id: Int): Curso = cursos.filter { c -> c.id == id }.first()
}