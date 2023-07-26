package com.example.escolasdosamba.dao

import jakarta.persistence.*
import org.hibernate.annotations.GeneratorType
import java.util.Date

@Entity
 @Table(name = "erl_escuela_de_samba")
data class Escuela(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(name="nombre", length = 50)
        var name: String,

        @Column(name="historia", length = 800)
        var history: String,

        @Column(name="fecha_fundacion")
        @Temporal(TemporalType.DATE)
        var foundationDate: Date,

        @Column(name="direccion_sede", length = 120)
        var address: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="id_lugar", nullable = false, insertable = false)
        var place: Lugar

)
