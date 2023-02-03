package br.com.alura.forum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class NavegapiApplication

fun main(args: Array<String>) {
	runApplication<br.com.alura.forum.NavegapiApplication>(*args)
}
