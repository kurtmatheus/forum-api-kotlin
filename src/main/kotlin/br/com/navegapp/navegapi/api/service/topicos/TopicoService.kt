package br.com.navegapp.navegapi.api.service.topicos

import br.com.navegapp.navegapi.api.exceptions.NotFoundException
import br.com.navegapp.navegapi.api.form.topico.AtualizarTopicoForm
import br.com.navegapp.navegapi.api.form.topico.TopicoForm
import br.com.navegapp.navegapi.api.mapper.topico.TopicoFormMapper
import br.com.navegapp.navegapi.api.mapper.topico.TopicoViewMapper
import br.com.navegapp.navegapi.api.view.topico.TopicoView
import br.com.navegapp.navegapi.domain.Topico
import org.apache.logging.log4j.util.Strings.isNotEmpty
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder

@Service
class TopicoService (
    private var topicos: MutableList<Topico> = mutableListOf(),
    private val formMapper: TopicoFormMapper,
    private val viewMapper: TopicoViewMapper,
    private val notFoundMessage: String = "Topico Não Encontrado"
) {

    fun listar(): List<TopicoView> = viewMapper.mapToList(topicos)

    fun buscarPorId(id: Int): TopicoView {
        val topico = topicos.find { t-> t.id == id } ?: throw NotFoundException(notFoundMessage)
        return viewMapper.map(topico)
    }

    fun cadastrar(form: TopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView> {
        val topico = formMapper.map(form)
        topico.id = topicos.size + 1

        topicos  = topicos.plus(topico).toMutableList()

        val uri = uriBuilder.path("/topicos/${topico.id}").build().toUri()
        return ResponseEntity.created(uri).body(viewMapper.map(topico))
    }

    fun atualizar(form: AtualizarTopicoForm, id: Int): ResponseEntity<TopicoView> {
        val topico = topicos.find { t-> t.id == id } ?: throw NotFoundException(notFoundMessage)
        topicos.minus(topico)
        topicos[id-1] = Topico(
            id = id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            dataCriacao = topico.dataCriacao,
            curso = topico.curso,
            autor = topico.autor,
            status = topico.status,
            respostas = topico.respostas
        )
        return ResponseEntity.ok(viewMapper.map(topicos[id-1]))
    }

    fun deletar(id: Int) {
        val topico = topicos.find { t-> t.id == id } ?: throw NotFoundException(notFoundMessage)
        topicos = topicos.minus(topico).toMutableList()
    }
}
