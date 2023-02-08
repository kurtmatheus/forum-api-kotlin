package br.com.alura.forum.domain

import br.com.alura.forum.api.view.curso.CursoView
import br.com.alura.forum.api.view.topico.TopicoView
import br.com.alura.forum.api.view.usuario.UsuarioView

val topico= TopicoTest.build()
object TopicoViewTest {
    fun build() = TopicoView (
        titulo = topico.titulo,
        mensagem = topico.mensagem,
        curso = CursoView(
            nome = topico.curso.nome,
            categoria = topico.curso.categoria
        ),
        autor = UsuarioView(
            nome = topico.autor.nome,
            email = topico.autor.email
        ),
        status = topico.status.toString(),
        dataCriacao = topico.dataCriacao,
        dataAlteracao = topico.dataCriacao
    )
}