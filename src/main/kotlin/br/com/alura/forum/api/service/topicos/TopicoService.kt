package br.com.alura.forum.api.service.topicos

import br.com.alura.forum.api.exceptions.NotFoundException
import br.com.alura.forum.api.form.topico.AtualizarTopicoForm
import br.com.alura.forum.api.form.topico.TopicoForm
import br.com.alura.forum.api.mapper.topico.TopicoFormMapper
import br.com.alura.forum.api.mapper.topico.TopicoViewMapper
import br.com.alura.forum.api.repository.topico.TopicoRepository
import br.com.alura.forum.api.view.topico.TopicoPorCategoriaView
import br.com.alura.forum.api.view.topico.TopicoView
import br.com.alura.forum.domain.Topico
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder
import java.time.LocalDateTime

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val viewMapper: TopicoViewMapper,
    private val formMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Topico Não Encontrado"
) {
    @Cacheable(cacheNames = ["topicos"], key = "#root.method.name")
    fun listar(nomeCurso: String?, page: Pageable): Page<TopicoView> {
        val topicos = nomeCurso?.let { repository.findByCursoNome(nomeCurso, page) } ?: repository.findAll(page)
        return topicos.map { t -> viewMapper.map(t) }
    }

    fun buscarPorId(id: Int): TopicoView {
        val topico = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        return viewMapper.map(topico)
    }

    @CacheEvict(cacheNames = ["topicos"], allEntries = true)
    fun cadastrar(form: TopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView> {
        val topico = repository.save(formMapper.map(form))
        val uri = uriBuilder.path("/topicos/${topico.id}").build().toUri()
        return ResponseEntity.created(uri).body(viewMapper.map(topico))
    }

    @CacheEvict(cacheNames = ["topicos"], allEntries = true)
    fun atualizar(form: AtualizarTopicoForm, id: Int): ResponseEntity<TopicoView> {
        val topico = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        topico.dataAlteracao = LocalDateTime.now()
        return ResponseEntity.ok(viewMapper.map(topico))
    }

    @CacheEvict(cacheNames = ["topicos"], allEntries = true)
    fun deletar(id: Int) {
        val topico = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        repository.delete(topico)
    }

    fun buscarTopico(id: Int): Topico = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }

    fun relatorioTopicoPorCategoria(): List<TopicoPorCategoriaView> = repository.relatorioTopicoByCursoCategoria()

}
