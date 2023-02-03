package br.com.alura.forum.api.config

import br.com.alura.forum.api.repository.usuario.UsuarioRepository
import br.com.alura.forum.api.service.usuario.UsuarioService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration (
    private val userRepository: UsuarioRepository
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http?.authorizeRequests()?.anyRequest()?.authenticated()?.and()?.sessionManagement()
            ?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()?.formLogin()?.disable()?.httpBasic()

        return http.build()
    }

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        return UsuarioService(userRepository)
    }
}