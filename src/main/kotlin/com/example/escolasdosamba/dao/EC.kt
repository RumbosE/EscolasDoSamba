package com.example.escolasdosamba.dao

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@Table(name = "erl_e_c")
data class EC(

    @EmbeddedId
    var id: EcId,

    @ManyToOne
    @JoinColumn(name = "id_escuela", referencedColumnName = "id", insertable=false, updatable=false)
    var escuelaEC: Escuela? = null,

    @ManyToOne
    @JoinColumn(name = "id_color", referencedColumnName = "id",insertable=false, updatable=false )
    var colorEC: Color? = null,

)

@Embeddable
data class EcId(

    @Column(name="id_escuela")
    var idEscuela:Long,

    @Column(name="id_color")
    var idColor : Long

): Serializable
