package br.com.alura.forum.api.form.resposta

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class RespostaForm(
    @field:NotEmpty val mensagem: String,
    @field:NotNull val topicoId: Int,
    @field:NotNull val usuarioId: Int,
    @field:NotNull val indicadorSolucao: Int
)
