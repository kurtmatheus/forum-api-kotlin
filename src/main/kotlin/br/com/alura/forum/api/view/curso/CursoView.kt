package br.com.alura.forum.api.view.curso

import java.io.Serializable

data class CursoView(
    val nome: String,
    val categoria: String
) : Serializable
