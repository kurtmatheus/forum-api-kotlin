package br.com.alura.forum.api.mapper.topico

import br.com.alura.forum.api.view.curso.CursoView
import br.com.alura.forum.api.view.topico.TopicoView
import br.com.alura.forum.api.view.usuario.UsuarioView
import br.com.alura.forum.domain.Topico
import br.com.alura.forum.util.Mapper
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper : Mapper<Topico, TopicoView> {
    override fun map(t: Topico): TopicoView {
        return TopicoView(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = CursoView(nome = t.curso.nome, categoria = t.curso.categoria),
            autor = UsuarioView(nome = t.autor.nome, email = t.autor.email),
            status = t.status.toString(),
            dataCriacao = t.dataCriacao
        )
    }

}
