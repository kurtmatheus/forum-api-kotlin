package br.com.alura.forum.api.repository.topicos

import br.com.alura.forum.api.repository.topico.TopicoRepository
import br.com.alura.forum.domain.TopicoTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicoRepositoryTestContainer {

    private val topico = TopicoTest.build()

    @Autowired
    private lateinit var repository: TopicoRepository

    @Suppress("DEPRECATION")
    companion object {
        @Container
        private val mySQLContainer = MySQLContainer<Nothing>("mysql:8.0.28").apply {
            withDatabaseName("forum-teste")
            withUsername("root")
            withPassword("")
        }

        @DynamicPropertySource
        @JvmStatic
        fun properties(registry: DynamicPropertyRegistry){
            registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl)
            registry.add("spring.datasource.username", mySQLContainer::getUsername)
            registry.add("spring.datasource.password", mySQLContainer::getPassword)
        }
    }
    
    @Test
    fun `dado um nome curso, entao deve retornar uma lista de topico por curso`(){
        repository.save(topico)
        val topicos = repository.findByCursoNome("Kotlin/Spring", PageRequest.of(0, 1))

        assertNotNull(topicos)
    }
}