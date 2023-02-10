package br.com.alura.forum.api.controller.topico

import br.com.alura.forum.api.config.JWTUtil
import br.com.alura.forum.domain.Curso
import br.com.alura.forum.domain.Role
import br.com.alura.forum.domain.Topico
import br.com.alura.forum.domain.Usuario
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = ["DB_NAME=forum-test", "spring.jpa.hibernate.ddlAuto:create-drop"])
@AutoConfigureTestEntityManager
@javax.transaction.Transactional
class TopicoControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var em: TestEntityManager

    @Autowired
    private lateinit var jwtUtil: JWTUtil
    private lateinit var mockMvc: MockMvc

    private var jwt: String? = null

    private lateinit var topico: Topico

    companion object {
        private const val URI = "/topicos/"
        private const val URI_ID = URI.plus("%s")
    }

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply<DefaultMockMvcBuilder>(
            SecurityMockMvcConfigurers.springSecurity()
        ).build()

        jwt = generateToken()

        val AUTOR = Usuario(nome = "Kurt Matheus", email = "kurt@email.com", password = "\$2a\$12\$T8XE/cYac/HkD77X2YP46eFvYxQ6YnqAlZyZ9W4NBnHbFSxt.Bqz2")

        val CURSO = Curso(nome = "Kotlin/Spring", categoria = "Programação")

        val TOPICO = Topico(titulo = "Como Mockar em Kotlin?", mensagem = "Como faço pra mockar dados em Kotlin?", curso = CURSO, autor = AUTOR)

        em.persist(AUTOR)
        em.persist(CURSO)
        topico = em.persist(TOPICO)
    }

    @Test
    fun `dado uma requisicao sem token, entao retorna erro 400`() {
        mockMvc.get(URI).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `dado uma requisicao com token, entao deve retornar lista de topicos com codigo 200`() {
        mockMvc.get(URI) {
            headers { jwt?.let { setBearerAuth(it) } }
        }.andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `dado uma requisicao com id de recurso, entao deve retornar lista de topicos com codigo 200`() {
        mockMvc.get(URI_ID.format("${topico.id}")) {
            headers { jwt?.let { setBearerAuth(it) } }
        }.andExpect { status { is2xxSuccessful() } }
    }

    private fun generateToken(): String? {
        val authorities = mutableListOf(Role(1, "LEITURA-ESCRITA"))
        return jwtUtil.generateToken(username = "kurt@email.com", authorities = authorities)
    }
}