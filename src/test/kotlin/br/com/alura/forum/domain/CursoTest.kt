package br.com.alura.forum.domain

import br.com.alura.forum.domain.Curso

object CursoTest {
    fun build(): Curso = Curso(
        id = 1,
        nome = "Kotlin/Spring",
        categoria = "Programação"
    )
}