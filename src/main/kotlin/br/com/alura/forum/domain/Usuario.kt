package br.com.alura.forum.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
    val email: String = "",
    val nome: String = "",
    val password: String = ""
)
