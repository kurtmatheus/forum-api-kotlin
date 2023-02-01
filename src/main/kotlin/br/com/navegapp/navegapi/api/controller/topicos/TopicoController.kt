package br.com.navegapp.navegapi.api.controller.topicos

import br.com.navegapp.navegapi.api.form.topico.AtualizarTopicoForm
import br.com.navegapp.navegapi.api.form.topico.TopicoForm
import br.com.navegapp.navegapi.api.service.topicos.TopicoService
import br.com.navegapp.navegapi.api.view.topico.TopicoView
import br.com.navegapp.navegapi.domain.Topico
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): List<TopicoView> = service.listar()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Int): ResponseEntity<TopicoView> = ResponseEntity.ok(service.buscarPorId(id))

    @PostMapping
    fun cadastrar(@RequestBody @Valid form: TopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView> {
        return service.cadastrar(form, uriBuilder)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody @Valid form: AtualizarTopicoForm, @PathVariable id: Int): ResponseEntity<TopicoView> = service.atualizar(form, id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Int) = service.deletar(id)
}
