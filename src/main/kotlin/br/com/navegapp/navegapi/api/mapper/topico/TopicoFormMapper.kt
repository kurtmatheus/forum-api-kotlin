package br.com.navegapp.navegapi.api.mapper.topico

import br.com.navegapp.navegapi.api.form.topico.TopicoForm
import br.com.navegapp.navegapi.api.service.curso.CursoService
import br.com.navegapp.navegapi.api.service.usuario.UsuarioService
import br.com.navegapp.navegapi.domain.Topico
import br.com.navegapp.navegapi.util.Mapper
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