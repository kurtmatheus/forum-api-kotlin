package br.com.alura.forum.api.service.topicos

import br.com.alura.forum.api.exceptions.NotFoundException
import br.com.alura.forum.api.mapper.topico.TopicoFormMapper
import br.com.alura.forum.api.mapper.topico.TopicoViewMapper
import br.com.alura.forum.api.repository.topico.TopicoRepository
import br.com.alura.forum.domain.TopicoTest
import br.com.alura.forum.domain.TopicoViewTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicoServiceTest {

    val topicos = PageImpl(listOf(TopicoTest.build()))
    val paginacao: Pageable = mockk()

    val repository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
        every { findAll(paginacao) } returns topicos
    }

    val topicoViewMapper: TopicoViewMapper = mockk {
        every { map(any()) } returns TopicoViewTest.build()
    }
    val topicoFormMapper: TopicoFormMapper = mockk()

    val topicoService: TopicoService = TopicoService (
        repository, topicoViewMapper, topicoFormMapper
    )

    @Test
    fun `dado um nome de curso, entao deve retornar lista de topicos com o nome do Curso`() {
        topicoService.listar("Kotlin/Spring", paginacao)

        verify(exactly = 1) { repository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { repository.findAll() }
    }

    @Test
    fun `dado um nome de curso null, entao deve retornar lista de todos os topicos`() {
        topicoService.listar(null, paginacao)

        verify(exactly = 0) { repository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 1) { repository.findAll(paginacao) }
    }

    @Test
    fun `dado um id inválido, entao retorna uma not found exception`() {
        every { repository.findById(any()) } returns Optional.empty()

        val atual = assertThrows<NotFoundException> {
            topicoService.buscarPorId(1)
        }

        assertEquals("Topico Não Encontrado", atual.message)
    }

}