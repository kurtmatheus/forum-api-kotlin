package br.com.alura.forum.api.view.usuario

import java.io.Serializable

data class UsuarioView(
    val nome: String,
    val email: String
) : Serializable
