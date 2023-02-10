package br.com.alura.forum.api.repository.resposta

import br.com.alura.forum.domain.Resposta
import org.springframework.data.repository.PagingAndSortingRepository

interface RespostaRepository : PagingAndSortingRepository<Resposta, Int>