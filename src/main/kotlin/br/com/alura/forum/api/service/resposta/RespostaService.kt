package br.com.alura.forum.api.service.resposta

import br.com.alura.forum.api.form.resposta.RespostaForm
import br.com.alura.forum.api.mapper.resposta.RespostaFormMapper
import br.com.alura.forum.api.mapper.topico.TopicoViewMapper
import br.com.alura.forum.api.repository.resposta.RespostaRepository
import br.com.alura.forum.api.service.EmailService
import br.com.alura.forum.api.service.topicos.TopicoService
import br.com.alura.forum.api.view.topico.TopicoView
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder

@Service
class RespostaService(
    private val repository: RespostaRepository,
    private val formMapper: RespostaFormMapper,
    private val topicoService: TopicoService,
    private val topicoViewMapper: TopicoViewMapper,
    private val emailService: EmailService
) {
    fun salvar(form: RespostaForm, uriBuilder: UriComponentsBuilder) : ResponseEntity<TopicoView> {
        val resposta = repository.save(formMapper.map(form))
        val topico = topicoService.buscarTopico(form.topicoId).apply {
            respostas = respostas.plus(resposta)
        }

        emailService.notificar(topico.autor.email)

        val uri = uriBuilder.path("/topicos/${topico.id}").build().toUri()
        return  ResponseEntity.created(uri).body(topicoViewMapper.map(topico))
    }
}
