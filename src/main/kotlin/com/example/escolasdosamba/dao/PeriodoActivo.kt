package com.example.escolasdosamba.dao

import jakarta.persistence.*
import java.io.Serializable
import java.sql.Date

@Entity
@Table(name = "erl_periodo_activo")
data class PeriodoActivo(

    @EmbeddedId
    var id: PeriodoActivoId,

    @Column(name = "autoridad")
    var authority: Char,

    @Column(name = "fecha_retiro")
    var endDate: Date? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_escuela", referencedColumnName = "id", insertable = false, updatable = false)
    val escuelaPeriodoActivo : Escuela?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_integrante", referencedColumnName = "id", insertable = false, updatable = false)
    val integrantePeriodoActivo : Integrante?


)

@Embeddable
data class PeriodoActivoId(

    @Column(name = "fecha_inscripcion")
    var registrationDate: Date,

    @Column(name = "id_escuela")
    var idEscuela: Long,

    @Column(name= "id_integrante")
    var idIntegrante: Long

) : Serializable

