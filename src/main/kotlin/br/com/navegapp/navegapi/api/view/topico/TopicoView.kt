package br.com.navegapp.navegapi.api.view.topico

import br.com.navegapp.navegapi.api.view.curso.CursoView
import br.com.navegapp.navegapi.api.view.usuario.UsuarioView

data class TopicoView(
    val titulo: String,
    val mensagem: String,
    val curso: CursoView,
    val autor: UsuarioView
)
