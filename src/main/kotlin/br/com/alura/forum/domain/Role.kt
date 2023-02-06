package br.com.alura.forum.domain

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private val id: Int,
    private val nome: String
) : GrantedAuthority{
    override fun getAuthority(): String = nome

}