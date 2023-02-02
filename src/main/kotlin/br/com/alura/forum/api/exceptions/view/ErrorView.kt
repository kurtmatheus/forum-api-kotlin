package br.com.alura.forum.api.exceptions.view

import java.time.LocalDateTime

data class ErrorView(
    val data: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)
