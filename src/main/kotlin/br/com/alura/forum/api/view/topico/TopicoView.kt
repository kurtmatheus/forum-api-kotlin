package br.com.alura.forum.api.view.topico

import br.com.alura.forum.api.view.curso.CursoView
import br.com.alura.forum.api.view.usuario.UsuarioView
import java.time.LocalDateTime

data class TopicoView(
    val titulo: String,
    val mensagem: String,
    val curso: CursoView,
    val autor: UsuarioView,
    val status: String,
    val dataCriacao: LocalDateTime
)
