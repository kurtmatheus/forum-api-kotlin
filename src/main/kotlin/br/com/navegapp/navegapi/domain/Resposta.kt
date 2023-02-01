package br.com.navegapp.navegapi.domain

import org.springframework.context.annotation.AutoProxyRegistrar
import java.time.LocalDateTime

data class Resposta(
    val id: Int? = null,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val autor: Usuario,
    val topico: Topico,
    val solucao: Boolean
)
