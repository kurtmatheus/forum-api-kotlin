package br.com.alura.forum.api.controller.topico

import br.com.alura.forum.api.form.topico.AtualizarTopicoForm
import br.com.alura.forum.api.form.topico.TopicoForm
import br.com.alura.forum.api.service.topicos.TopicoService
import br.com.alura.forum.api.view.topico.TopicoView
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    @Cacheable("topicos")
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) page: Pageable
    ): Page<TopicoView> = service.listar(nomeCurso, page)

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Int): ResponseEntity<TopicoView> = ResponseEntity.ok(service.buscarPorId(id))

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun cadastrar(@RequestBody @Valid form: TopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView> {
        return service.cadastrar(form, uriBuilder)
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizar(@RequestBody @Valid form: AtualizarTopicoForm, @PathVariable id: Int): ResponseEntity<TopicoView> = service.atualizar(form, id)

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Int) = service.deletar(id)
}
