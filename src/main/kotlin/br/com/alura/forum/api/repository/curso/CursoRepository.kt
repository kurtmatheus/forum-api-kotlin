package br.com.alura.forum.api.repository.curso

import br.com.alura.forum.domain.Curso
import org.springframework.data.repository.PagingAndSortingRepository

interface CursoRepository : PagingAndSortingRepository<Curso, Int>