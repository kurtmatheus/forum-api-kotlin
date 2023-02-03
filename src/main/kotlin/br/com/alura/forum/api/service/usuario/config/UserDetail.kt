package br.com.alura.forum.api.service.usuario.config

import br.com.alura.forum.domain.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val usuario: Usuario) : UserDetails {
    override fun getAuthorities() = null

    override fun getPassword() = usuario.password

    override fun getUsername() = usuario.password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}