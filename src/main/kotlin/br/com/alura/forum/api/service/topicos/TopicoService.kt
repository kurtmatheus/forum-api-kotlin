package br.com.alura.forum.api.service.topicos

import br.com.alura.forum.api.exceptions.NotFoundException
import br.com.alura.forum.api.form.topico.AtualizarTopicoForm
import br.com.alura.forum.api.form.topico.TopicoForm
import br.com.alura.forum.api.mapper.topico.TopicoFormMapper
import br.com.alura.forum.api.mapper.topico.TopicoViewMapper
import br.com.alura.forum.api.repository.topico.TopicoRepository
import br.com.alura.forum.api.view.topico.TopicoView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val formMapper: TopicoFormMapper,
    private val viewMapper: TopicoViewMapper,
    private val notFoundMessage: String = "Topico Não Encontrado"
) {
    fun listar(nomeCurso: String?, page: Pageable): Page<TopicoView> {
        var topicos = if (nomeCurso == null) {
            repository.findAll(page)
        } else {
            repository.findByCursoNome(nomeCurso, page)
        }
        return topicos.map { t -> viewMapper.map(t) }
    }

    fun buscarPorId(id: Int): TopicoView {
        val topico = repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        return viewMapper.map(topico)
    }

    fun cadastrar(form: TopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView> {
        val topico = repository.save(formMapper.map(form))
        val uri = uriBuilder.path("/topicos/${topico.id}").build().toUri()
        return ResponseEntity.created(uri).body(viewMapper.map(topico))
    }

    fun atualizar(form: AtualizarTopicoForm, id: Int): ResponseEntity<TopicoView> {
        var topico = repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return ResponseEntity.ok(viewMapper.map(topico))
    }

    fun deletar(id: Int) {
        val topico = repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        repository.delete(topico)
    }
}
