package br.com.alura.forum.api.mapper.topico

import br.com.alura.forum.api.form.topico.TopicoForm
import br.com.alura.forum.api.service.curso.CursoService
import br.com.alura.forum.api.service.usuario.UsuarioService
import br.com.alura.forum.domain.Topico
import br.com.alura.forum.util.Mapper
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper (
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
): Mapper<TopicoForm, Topico> {
    override fun map(t: TopicoForm): Topico = Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor)
        )
}