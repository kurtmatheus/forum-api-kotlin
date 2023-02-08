package br.com.alura.forum.domain


object TopicoTest {
    fun build(): Topico = Topico(
        id = 1,
        titulo = "Como Mockar em Kotlin?",
        mensagem = "Como faço pra mockar dados em Kotlin?",
        curso = CursoTest.build(),
        autor = UsuarioTest.build()
    )
}
