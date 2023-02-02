package br.com.alura.forum.api.form.topico

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicoForm(
    @field:NotEmpty @field:Size(min = 5, max = 100, message = "Titulo deve ter entre 5 e 100 caracteres") val titulo: String,
    @field:NotEmpty(message = "Mensagem não pode estar vazia")  val mensagem: String,
    @field:NotNull val idCurso: Int,
    @field:NotNull val idAutor: Int
) 