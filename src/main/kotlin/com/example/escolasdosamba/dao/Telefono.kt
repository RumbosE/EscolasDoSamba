package com.example.escolasdosamba.dao

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name="erl_telefono")
data class Telefono(

    @EmbeddedId
    var id: TelefonoId,

    @Column(name="tipo")
    var tipo: Char,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_escuela", referencedColumnName = "id")
    var escuelaTelefono: Escuela? = null

) {
    override fun toString() = "(${id.codigo}) - ${id.numero}"
}

@Embeddable
data class TelefonoId(

    @Column(name="codigo")
    var codigo: Long,

    @Column(name="numero")
    var numero: Long

): Serializable

