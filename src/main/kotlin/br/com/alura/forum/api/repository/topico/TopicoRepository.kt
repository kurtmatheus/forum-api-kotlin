package br.com.alura.forum.api.repository.topico

import br.com.alura.forum.api.view.topico.TopicoPorCategoriaView
import br.com.alura.forum.domain.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface TopicoRepository : PagingAndSortingRepository<Topico, Int> {
    fun findByCursoNome(nomeCurso: String, page: Pageable): Page<Topico>

    @Query(value = "SELECT new br.com.alura.forum.api.view.topico.TopicoPorCategoriaView(c.categoria, count(t))" +
            " FROM Topico t INNER JOIN t.curso c GROUP BY c.categoria")
    fun relatorioTopicoByCursoCategoria(): List<TopicoPorCategoriaView>
}