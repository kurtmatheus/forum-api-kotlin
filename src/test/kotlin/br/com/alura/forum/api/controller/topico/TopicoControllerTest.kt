package br.com.alura.forum.api.controller.topico

import br.com.alura.forum.api.config.JWTUtil
import br.com.alura.forum.domain.Role
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicoControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext
    @Autowired
    private lateinit var jwtUtil: JWTUtil
    private lateinit var mockMvc: MockMvc

    private var jwt: String? = null
    companion object {
        private const val RECURSO = "/topicos/"
        private const val RECURSO_ID = RECURSO.plus("%s")
    }

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply<DefaultMockMvcBuilder>(
            SecurityMockMvcConfigurers.springSecurity()
        ).build()

        jwt = generateToken()
    }

    @Test
    fun `dado uma requisicao sem token, entao retorna erro 400`() {
        mockMvc.get(RECURSO).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `dado uma requisicao com token, entao deve retornar lista de topicos com codigo 200`() {
        mockMvc.get(RECURSO) {
            headers { jwt?.let { setBearerAuth(it) } }
        }.andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `dado uma requisicao com id de recurso, entao deve retornar lista de topicos com codigo 200`() {
        mockMvc.get(RECURSO_ID.format(1)) {
            headers { jwt?.let { setBearerAuth(it) } }
        }.andExpect { status { is2xxSuccessful() } }
    }

    private fun generateToken(): String? {
        val authorities = mutableListOf(Role(1, "LEITURA-ESCRITA"))
        return jwtUtil.generateToken(username = "dani@email.com", authorities = authorities)
    }
}