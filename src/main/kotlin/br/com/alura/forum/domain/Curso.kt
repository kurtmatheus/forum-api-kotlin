package br.com.alura.forum.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Curso(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
    val nome: String = "",
    val categoria: String = ""
)

