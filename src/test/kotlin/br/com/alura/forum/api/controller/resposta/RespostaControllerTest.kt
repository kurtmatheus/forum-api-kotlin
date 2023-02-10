package br.com.alura.forum.api.controller.resposta

import br.com.alura.forum.api.config.JWTUtil
import br.com.alura.forum.domain.Curso
import br.com.alura.forum.domain.Role
import br.com.alura.forum.domain.Topico
import br.com.alura.forum.domain.Usuario
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = ["DB_NAME=forum-test", "spring.jpa.hibernate.ddlAuto:create-drop"])
@AutoConfigureTestEntityManager
@javax.transaction.Transactional
class RespostaControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var em: TestEntityManager

    @Autowired
    private lateinit var jwtUtil: JWTUtil
    private lateinit var mockMvc: MockMvc

    private var jwt: String? = null

    companion object {
        private const val RECURSO = "/respostas/"

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
        em.clear()

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply<DefaultMockMvcBuilder>(
            SecurityMockMvcConfigurers.springSecurity()
        ).build()

        jwt = generateToken()

        em.persist(AUTOR)
        em.persist(CURSO)
        em.persist(TOPICO)
    }

    @Test
    fun `dado as informacoes passadas no form, entao retorna o topico com a nova resposta 200`() {
        val respostaJson = """
            {
                "mensagem": "resposta teste",
                "topicoId": 1,
                "usuarioId": 1,
                "indicadorSolucao":0
            }
        """.trimMargin()

        mockMvc.post(RECURSO) {
            headers { jwt?.let { setBearerAuth(it) }
                contentType = MediaType.APPLICATION_JSON
                content = respostaJson
            }
        }.andExpect { status { is2xxSuccessful() } }
    }
    private fun generateToken(): String? {
        val authorities = mutableListOf(Role(1, "LEITURA-ESCRITA"))
        return jwtUtil.generateToken(username = "kurt@email.com", authorities = authorities)
    }
}