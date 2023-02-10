package br.com.alura.forum.api.repository.topicos

import br.com.alura.forum.api.controller.resposta.RespostaControllerTest
import br.com.alura.forum.api.repository.topico.TopicoRepository
import br.com.alura.forum.domain.Curso
import br.com.alura.forum.domain.Topico
import br.com.alura.forum.domain.TopicoTest
import br.com.alura.forum.domain.Usuario
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.TestPropertySource


@DataJpaTest
@TestPropertySource(properties = ["DB_NAME=forum-test", "spring.jpa.hibernate.ddlAuto:create-drop"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicoRepositoryTest {

    private val topico = TopicoTest.build()

    @Autowired
    private val em: TestEntityManager? = null

    @Autowired
    private lateinit var repository: TopicoRepository

    companion object {
        private val AUTOR: Usuario = Usuario (
            nome = "Kurt Matheus",
            email = "kurt@email.com",
            password = "\$2a\$12\$T8XE/cYac/HkD77X2YP46eFvYxQ6YnqAlZyZ9W4NBnHbFSxt.Bqz2"
        )

        private val CURSO: Curso = Curso(
            nome = "Kotlin/Spring",
            categoria = "Programação"
        )

        private val TOPICO: Topico = Topico(
            titulo = "Como Mockar em Kotlin?",
            mensagem = "Como faço pra mockar dados em Kotlin?",
            curso = CURSO,
            autor = AUTOR
        )
    }

    @BeforeEach
    fun setup() {
        em?.persist(CURSO)
        em?.persist(AUTOR)
        em?.persist(TOPICO)
    }
    
    @Test
    fun `dado um nome curso, entao deve retornar uma lista de topico por curso`(){
        val topicos = repository.findByCursoNome("Kotlin/Spring", PageRequest.of(0, 1))

        assertNotNull(topicos)
    }
}