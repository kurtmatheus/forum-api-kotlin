package br.com.alura.forum.api.repository.topicos

import br.com.alura.forum.api.repository.topico.TopicoRepository
import br.com.alura.forum.domain.TopicoTest
import org.junit.jupiter.api.Assertions.assertNotNull
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
    
    @Test
    fun `dado um nome curso, entao deve retornar uma lista de topico por curso`(){
        repository.save(topico)
        val topicos = repository.findByCursoNome("Kotlin/Spring", PageRequest.of(0, 1))

        assertNotNull(topicos)
    }
}