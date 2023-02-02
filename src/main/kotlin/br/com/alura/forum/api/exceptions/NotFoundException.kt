package br.com.alura.forum.api.exceptions

class NotFoundException(notFoundMessage: String) : RuntimeException(notFoundMessage) {

}
