//package br.com.alura.forum.api.repository.topicos
//
//import br.com.alura.forum.api.config.ContainerConfiguration
//import br.com.alura.forum.api.repository.topico.TopicoRepository
//import br.com.alura.forum.api.view.topico.TopicoPorCategoriaView
//import br.com.alura.forum.domain.TopicoTest
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
//import org.springframework.data.domain.PageRequest
//import org.testcontainers.junit.jupiter.Testcontainers
//
//@Testcontainers
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class TopicoRepositoryTestContainer : ContainerConfiguration(){
//
//    @Autowired
//    private lateinit var topicoRepository: TopicoRepository
//
//    private val paginacao = PageRequest.of(0,5)
//    private val topico = TopicoTest.build()
//
//    @Test
//    fun `deve gerar um relatorio`() {
//        topicoRepository.save(topico)
//        val relatorio = topicoRepository.relatorioTopicoByCursoCategoria()
//
//        assertThat(relatorio).isNotNull
//        assertThat(relatorio.first()).isExactlyInstanceOf(TopicoPorCategoriaView::class.java)
//    }
//
//    @Test
//    fun `deve buscar um topico por nome`() {
//        topicoRepository.save(topico)
//        val resultado = topicoRepository.findByCursoNome(nomeCurso = "Kotlin", page = paginacao)
//
//        assertThat(resultado.totalElements).isEqualTo(1)
//    }
//}
