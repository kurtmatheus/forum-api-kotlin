package br.com.alura.forum.api.repository.topico

import br.com.alura.forum.domain.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface TopicoRepository : PagingAndSortingRepository<Topico, Int> {
    fun findByCursoNome(nomeCurso: String, page: Pageable): Page<Topico>
}