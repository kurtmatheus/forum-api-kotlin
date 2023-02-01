package br.com.navegapp.navegapi.api.mapper.topico

import br.com.navegapp.navegapi.api.view.curso.CursoView
import br.com.navegapp.navegapi.api.view.topico.TopicoView
import br.com.navegapp.navegapi.api.view.usuario.UsuarioView
import br.com.navegapp.navegapi.domain.Topico
import br.com.navegapp.navegapi.util.Mapper
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper : Mapper<Topico, TopicoView> {
    override fun map(t: Topico): TopicoView {
        return TopicoView(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = CursoView(nome = t.curso.nome, categoria = t.curso.categoria),
            autor = UsuarioView(nome = t.autor.nome, email = t.autor.email)
        )
    }

    fun mapToList(topicos: List<Topico>): List<TopicoView> {
        return topicos.map { t -> TopicoView(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = CursoView(nome = t.curso.nome, categoria = t.curso.categoria),
            autor = UsuarioView(nome = t.autor.nome, email = t.autor.email)
        ) }
    }

}
