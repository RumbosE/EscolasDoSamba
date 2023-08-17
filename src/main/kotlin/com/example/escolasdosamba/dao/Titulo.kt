package com.example.escolasdosamba.dao

import jakarta.persistence.*
import java.io.Serializable
import java.sql.Date
import java.util.Calendar

@Entity
@Table(name="erl_hist_titulo")
data class Titulo(

    @EmbeddedId
    var id: TituloId,

    @Column(name="monto$", nullable = true)
    var monto: Float?,

    @Column(name="grupo", length = 40)
    var grupo: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_escuela", referencedColumnName = "id", insertable = false, updatable = false)
    val escuelaTitulo: Escuela? = null

) {
    fun getYear(): Int {
        val cal = Calendar.getInstance()
        cal.time = this.id.year
        return cal.get(Calendar.YEAR)
    }
}

@Embeddable
data class TituloId(

    @Column(name="id_escuela")
    var idEscuela: Long,

    @Column(name="año")
    var year: Date,

    ): Serializable
