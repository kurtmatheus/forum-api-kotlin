package br.com.alura.forum.api.mapper.resposta

import br.com.alura.forum.api.form.resposta.RespostaForm
import br.com.alura.forum.api.service.topicos.TopicoService
import br.com.alura.forum.api.service.usuario.UsuarioService
import br.com.alura.forum.domain.Resposta
import br.com.alura.forum.util.Mapper
import org.springframework.stereotype.Component

@Component
class RespostaFormMapper  (
    private val topicoService: TopicoService,
    private val usuarioService: UsuarioService
) : Mapper<RespostaForm, Resposta> {

    override fun map(r: RespostaForm): Resposta = Resposta(
        mensagem = r.mensagem,
        topico = topicoService.buscarTopico(r.topicoId),
        autor = usuarioService.buscarPorId(r.usuarioId),
        solucao = r.indicadorSolucao.let{ indcsolucao -> indcsolucao == 1 }
    )
}
