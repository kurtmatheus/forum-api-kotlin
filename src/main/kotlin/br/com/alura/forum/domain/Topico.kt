package br.com.alura.forum.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,
    var titulo: String = "",
    var mensagem: String = "",
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @ManyToOne val curso: Curso = Curso(),
    @ManyToOne val autor: Usuario = Usuario(),
    @Enumerated(value = EnumType.STRING) val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    @JsonIgnore @OneToMany(mappedBy = "topico") val respostas: List<Resposta> = ArrayList(),
    var dataAlteracao: LocalDateTime? = null
)