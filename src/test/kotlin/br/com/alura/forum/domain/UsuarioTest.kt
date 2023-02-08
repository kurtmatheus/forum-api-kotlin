package br.com.alura.forum.domain

object UsuarioTest {
    fun build(): Usuario = Usuario (
        id = 1,
        nome = "Kurt Matheus",
        email = "kurt@email.com",
        password = "\$2a\$12\$T8XE/cYac/HkD77X2YP46eFvYxQ6YnqAlZyZ9W4NBnHbFSxt.Bqz2"
    )

}
