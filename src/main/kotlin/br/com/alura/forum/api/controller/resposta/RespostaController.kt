package br.com.alura.forum.api.controller.resposta

import br.com.alura.forum.api.form.resposta.RespostaForm
import br.com.alura.forum.api.service.resposta.RespostaService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/respostas")
class RespostaController(
    private val service: RespostaService
) {
    @PostMapping
    @Transactional
    fun salvarResposta(
        @RequestBody @Valid form: RespostaForm,
        uriComponentsBuilder: UriComponentsBuilder
    ) = service.salvar(form, uriComponentsBuilder)
}