package com.example.escolasdosamba.dao

import jakarta.persistence.*
import java.io.Serializable
import java.sql.Date

@Entity
@Table(name = "erl_ganador")
data class Ganador(

    @EmbeddedId
    var id: GanadorId,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_escuela", referencedColumnName = "id", insertable = false, updatable = false)
    var escuelaPremio : Escuela? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_premio", referencedColumnName = "id", insertable = false, updatable = false)
    val premioGanador : PremioEspecial

)


@Embeddable
data class GanadorId(

    @Column(name = "año")
    var year: Date,

    @Column(name = "id_premio")
    var idPremio: Long

) : Serializable