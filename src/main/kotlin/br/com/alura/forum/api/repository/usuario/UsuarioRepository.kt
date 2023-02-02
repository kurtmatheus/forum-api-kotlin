package br.com.alura.forum.api.repository.usuario

import br.com.alura.forum.domain.Usuario
import org.springframework.data.repository.PagingAndSortingRepository

interface UsuarioRepository : PagingAndSortingRepository<Usuario, Int>