package br.com.alura.forum.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
    val email: String = "",
    val nome: String = "",
    val password: String = "",

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_role")
    val role:List<Role> = mutableListOf()
)
