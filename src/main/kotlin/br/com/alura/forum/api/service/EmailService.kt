package br.com.alura.forum.api.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
) {
    @Autowired
    private val javaMailSender: JavaMailSender? = null

    fun notificar(email: String) {
        val message = SimpleMailMessage()

        message.subject = "[ALURA] Tópico Respondido"
        message.text = "Seu Tópico recebeu uma resposta, Vamos conferir?"
        message.setTo(email)

        javaMailSender?.send(message)
    }
}