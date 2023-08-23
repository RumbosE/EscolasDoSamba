package com.example.escolasdosamba.dao

import jakarta.persistence.*
import java.util.Date

@SequenceGenerator(name = "escuela_generator", sequenceName = "erl_escuela_de_samba_id_seq", allocationSize = 1)

@Entity
 @Table(name = "erl_escuela_de_samba")
data class Escuela(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "escuela_generator")
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
        @JoinColumn(name="id_lugar", nullable = false)
        var place: Lugar,

        @OneToMany(mappedBy = "escuelaTelefono")
        var telefonos: MutableList<Telefono> = mutableListOf(),

        @OneToMany(mappedBy = "escuelaTitulo")
        var titulos: MutableList<Titulo> = mutableListOf(),

        @OneToMany(mappedBy = "escuelaPremio")
        var premios: MutableList<Ganador> = mutableListOf(),

        @OneToMany(mappedBy = "escuelaPeriodoActivo")
        var periodosActivos: MutableList<PeriodoActivo> = mutableListOf(),

        @ManyToMany
        @JoinTable(
                name= "erl_e_c",
                joinColumns = [JoinColumn(name="id_escuela")],
                inverseJoinColumns = [JoinColumn(name="id_color")]
        )
        var colors: MutableList<Color> = mutableListOf(),

)
