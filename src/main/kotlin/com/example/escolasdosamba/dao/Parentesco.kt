package com.example.escolasdosamba.dao

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "erl_parentesco")
data class Parentesco(

    @EmbeddedId
    val id: ParentescoId,

    @Column(name = "nombre")
    val relation: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_integrante1", insertable = false, updatable = false)
    val integrante1: Integrante,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_integrante2", insertable = false, updatable = false)
    val integrante2: Integrante,

)

@Embeddable
data class ParentescoId(

    @Column(name = "id_integrante1")
    val idIntegrante1: Long,

    @Column(name = "id_integrante2")
    val idIntegrante2: Long,

): Serializable
